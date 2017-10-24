package osp.leobert.android.router.compiler.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import osp.leobert.android.router.compiler.Constants;
import osp.leobert.android.router.compiler.Logger;
import osp.leobert.android.router.compiler.utils.TypeUtils;
import osp.leobert.android.router.facade.annotation.Autowired;
import osp.leobert.android.router.facade.enums.Type;

import static javax.lang.model.element.Modifier.PUBLIC;
import static osp.leobert.android.router.compiler.Constants.ANNOTATION_TYPE_AUTOWIRED;
import static osp.leobert.android.router.compiler.Constants.KEY_MODULE_NAME;

/**
 * <p><b>Package:</b> osp.leobert.android.router.compiler.processor </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AutowiredProcessor </p>
 * <p><b>Description:</b> Autowired Processor,Only Activity and Fragment/Fragment_V4 allowed  </p>
 * Created by leobert on 2017/10/23.
 */

@AutoService(Processor.class)
@SupportedOptions(KEY_MODULE_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
//@SupportedAnnotationTypes({ANNOTATION_TYPE_AUTOWIRED})
public class AutowiredProcessor extends AbstractProcessor {
    private static final String TAG = AutowiredProcessor.class.getSimpleName();
    private Logger logger;
    private Filer mFiler;
    private Types types;
    private TypeUtils typeUtils;
    private Elements elements;

    /**
     * Contain field need autowired and his super class.
     */
    private Map<TypeElement, List<Element>> parentAndChild = new HashMap<>();

    private static final ClassName AndroidLog = ClassName.get("android.util", "Log");

    private static final String SUFFIX_AUTOWIRED = "$$Router$$Autowired";

    private String PATH_TYPE_ISYRINGE = "osp.leobert.android.common.di.ISyringe";

    private String PATH_TYPE_JSON_SERVICE = "osp.leobert.android.common.json.JsonService";

    /*
    *  path_util_paramparser: 'osp.leobert.android.component.router.utils.ParamsUtils',
                        path_json_service    : 'osp.leobert.android.common.json.JsonService',
                        path_isyringe        : 'osp.leobert.android.common.di.ISyringe'

    * */

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        logger = new Logger(processingEnv.getMessager());   // Package the log utils.
        logger.info(">>>>parse options<<<<  :"+processingEnvironment.getOptions().size());
        parseOptionParams(processingEnvironment.getOptions());

        mFiler = processingEnv.getFiler();                  // Generate class.
        types = processingEnv.getTypeUtils();            // Get type utils.
        elements = processingEnv.getElementUtils();      // Get class meta.

        typeUtils = new TypeUtils(types, elements);


        logger.info(">>> AutowiredProcessor init. <<<");
    }

    private void parseOptionParams(Map<String, String> options) {
        if (MapUtils.isNotEmpty(options)) {
            PATH_TYPE_ISYRINGE = options.get("path_isyringe");
            PATH_TYPE_JSON_SERVICE = options.get("path_json_service");
        }


        if (StringUtils.isEmpty(PATH_TYPE_ISYRINGE)) {
            logger.error("set 'path_isyringe' in options");
            throw new IllegalStateException("no path_isyringe found");
        }
        if (StringUtils.isEmpty(PATH_TYPE_JSON_SERVICE)) {
            logger.error("set 'path_json_service' in options");
            throw new IllegalStateException("no path_json_service found");
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Collections.singletonList(
                ANNOTATION_TYPE_AUTOWIRED
        ));
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (CollectionUtils.isNotEmpty(set)) {
            try {
                logger.info(">>> Found autowired field, start... <<<");
                categories(roundEnvironment.getElementsAnnotatedWith(Autowired.class));
                generateHelper();

            } catch (Exception e) {
                logger.error(e);
            }
            return true;
        }

        return false;
    }

    private void generateHelper() throws IOException, IllegalAccessException {
        TypeElement type_ISyringe = elements.getTypeElement(PATH_TYPE_ISYRINGE);

        TypeElement type_JsonService = elements.getTypeElement(PATH_TYPE_JSON_SERVICE);

        TypeMirror activityTm = elements.getTypeElement(Constants.ACTIVITY).asType();
        TypeMirror fragmentTm = elements.getTypeElement(Constants.FRAGMENT).asType();
        TypeMirror fragmentTmV4 = elements.getTypeElement(Constants.FRAGMENT_V4).asType();

        // Build input param name.
        ParameterSpec objectParamSpec = ParameterSpec.builder(TypeName.OBJECT, "target").build();

        if (MapUtils.isNotEmpty(parentAndChild)) {
            for (Map.Entry<TypeElement, List<Element>> entry : parentAndChild.entrySet()) {
                // Build method : 'inject'
                MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                        .addAnnotation(Override.class)
                        .addModifiers(PUBLIC)
                        .addParameter(objectParamSpec);

                TypeElement parent = entry.getKey();
                List<Element> childs = entry.getValue();

                String qualifiedName = parent.getQualifiedName().toString();
                String packageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));

                String fileName = parent.getSimpleName() + SUFFIX_AUTOWIRED;

                logger.info(">>> Start process " + childs.size() + " field in " + parent.getSimpleName() + " ... <<<");

                TypeSpec.Builder helper = TypeSpec.classBuilder(fileName)
                        .addJavadoc("Auto generated by " + TAG)
                        .addSuperinterface(ClassName.get(type_ISyringe))
                        .addModifiers(PUBLIC);

                FieldSpec jsonServiceField = FieldSpec.builder(TypeName.get(type_JsonService.asType()),
                        "jsonService", Modifier.PRIVATE).build();
                helper.addField(jsonServiceField);

                logger.info("======== inject jsonservice");

                injectMethodBuilder.addStatement("jsonService = $T.Factory.getInstance().create()",
                        ClassName.get(type_JsonService));

                injectMethodBuilder.addStatement("$T substitute = ($T)target", ClassName.get(parent), ClassName.get(parent));

                // Generate method body, start inject.
                for (Element element : childs) {
                    Autowired fieldConfig = element.getAnnotation(Autowired.class);
                    String fieldName = element.getSimpleName().toString();
                    String originalValue = "substitute." + fieldName;
                    String statment = "substitute." + fieldName + " = substitute.";
                    boolean isActivity = false;
                    if (types.isSubtype(parent.asType(), activityTm)) {  // Activity, then use getIntent()
                        isActivity = true;
                        statment += "getIntent().";
                    } else if (types.isSubtype(parent.asType(), fragmentTm) ||
                            types.isSubtype(parent.asType(), fragmentTmV4)) {   // Fragment, then use getArguments()
                        statment += "getArguments().";
                    } else {
                        throw new IllegalAccessException("The field [" + fieldName + "] need " +
                                "autowired from intent, its parent must be activity or fragment!");
                    }

                    statment = buildStatement(originalValue, statment, typeUtils.typeExchange(element), isActivity);
                    if (statment.startsWith("jsonService.")) {   // Not mortals
                        injectMethodBuilder.beginControlFlow("if (null != jsonService)");
                        injectMethodBuilder.addStatement(
                                "substitute." + fieldName + " = " + statment,
                                (StringUtils.isEmpty(fieldConfig.name()) ? fieldName : fieldConfig.name()),
                                ClassName.get(element.asType())
                        );
                        injectMethodBuilder.nextControlFlow("else");
                        injectMethodBuilder.addStatement(
                                "$T.e(\"" + TAG + "\", \"You want automatic inject the field '"
                                        + fieldName + "' in class '$T' ," +
                                        " but JsonService not found in Router\")", AndroidLog, ClassName.get(parent));

                        injectMethodBuilder.endControlFlow();
                    } else {
                        injectMethodBuilder.addStatement(statment, StringUtils.isEmpty(fieldConfig.name()) ? fieldName : fieldConfig.name());
                    }

                    // Validator
                    if (fieldConfig.required() && !element.asType().getKind().isPrimitive()) {  // Primitive wont be check.
                        injectMethodBuilder.beginControlFlow("if (null == substitute." + fieldName + ")");
                        injectMethodBuilder.addStatement(
                                "$T.e(\"" + TAG + "\", \"The field '" + fieldName + "' is null, in class '\" + $T.class.getName() + \"!\")", AndroidLog, ClassName.get(parent));
                        injectMethodBuilder.endControlFlow();
                    }
                }

                helper.addMethod(injectMethodBuilder.build());

                // Generate autowire helper
                JavaFile.builder(packageName, helper.build()).build().writeTo(mFiler);

                logger.info(">>> " + parent.getSimpleName() + " has been processed, " + fileName + " has been generated. <<<");
            }

            logger.info(">>> Autowired processor stop. <<<");
        }
    }

    /**
     * @param originalValue bundleKey in the bundle of Intent
     * @param statement     original statement
     * @param type          type of data in the  bundle
     * @param isActivity    true as Activity, false as Fragment/Fragment_V4
     * @return statement
     */
    private String buildStatement(String originalValue, String statement, int type, boolean isActivity) {

        //Activity.getIntent().getXXExtra(); Fragment.getIntent().getXX();

        if (type == Type.BOOLEAN.ordinal()) {
            statement += (isActivity ? ("getBooleanExtra($S, " + originalValue + ")") : ("getBoolean($S)"));
        } else if (type == Type.BYTE.ordinal()) {
            statement += (isActivity ? ("getByteExtra($S, " + originalValue + ")") : ("getByte($S)"));
        } else if (type == Type.SHORT.ordinal()) {
            statement += (isActivity ? ("getShortExtra($S, " + originalValue + ")") : ("getShort($S)"));
        } else if (type == Type.INT.ordinal()) {
            statement += (isActivity ? ("getIntExtra($S, " + originalValue + ")") : ("getInt($S)"));
        } else if (type == Type.LONG.ordinal()) {
            statement += (isActivity ? ("getLongExtra($S, " + originalValue + ")") : ("getLong($S)"));
        } else if (type == Type.CHAR.ordinal()) {
            statement += (isActivity ? ("getCharExtra($S, " + originalValue + ")") : ("getChar($S)"));
        } else if (type == Type.FLOAT.ordinal()) {
            statement += (isActivity ? ("getFloatExtra($S, " + originalValue + ")") : ("getFloat($S)"));
        } else if (type == Type.DOUBLE.ordinal()) {
            statement += (isActivity ? ("getDoubleExtra($S, " + originalValue + ")") : ("getDouble($S)"));
        } else if (type == Type.STRING.ordinal()) {
            statement += (isActivity ? ("getStringExtra($S)") : ("getString($S)"));
        } else if (type == Type.PARCELABLE.ordinal()) {
            statement += (isActivity ? ("getParcelableExtra($S)") : ("getParcelable($S)"));
        } else if (type == Type.OBJECT.ordinal()) {
            statement = "jsonService.parseObject(substitute." +
                    (isActivity ? "getIntent()." : "getArguments().") +
                    (isActivity ? "getStringExtra($S)" : "getString($S)") + ", $T.class)";
        }

        return statement;
    }

    /**
     * @param elements Field need autowired
     */
    private void categories(Set<? extends Element> elements) throws IllegalAccessException {
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();

                if (element.getModifiers().contains(Modifier.PRIVATE)) {
                    throw new IllegalAccessException("The autowired fields CAN NOT BE 'private'!!! please check field ["
                            + element.getSimpleName() + "] in class [" + enclosingElement.getQualifiedName() + "]");
                }

                if (parentAndChild.containsKey(enclosingElement)) { // Has categries
                    parentAndChild.get(enclosingElement).add(element);
                } else {
                    List<Element> childs = new ArrayList<>();
                    childs.add(element);
                    parentAndChild.put(enclosingElement, childs);
                }
            }

            logger.info("categories finished.");
        }
    }

}

