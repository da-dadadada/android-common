/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package osp.leobert.android.router.compiler.utils;

import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import osp.leobert.android.router.compiler.model.MethodInfo;

/**
 * <p><b>Package:</b> osp.leobert.android.router.compiler.utils </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AnnoUtils </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/23.
 */

public class AnnoUtils {
    public static MethodInfo generateMethodInfo(ExecutableElement methodElement) {
        //modifiers
        ArrayList<Modifier> methodModifiers = new ArrayList<>();
        methodModifiers.add(Modifier.PUBLIC);
        //name
        String methodName = getSimpleName(methodElement);
        //params
        List<VariableElement> methodParams = new ArrayList<>();
        for (VariableElement typeParameterElement : methodElement.getParameters()) {
            methodParams.add(typeParameterElement);
        }
        //return type
        TypeMirror methodReturnType = methodElement.getReturnType();
        return new MethodInfo().setMethodName(methodName)
                .setMethodModifiers(methodModifiers)
                .setMethodParameters(methodParams)
                .setMethodReturnType(methodReturnType);
    }

    public static String getSimpleName(Element element) {
        return element.getSimpleName().toString();
    }

    public static ParameterSpec generateMethodParameterSpec(TypeMirror typeMirror,
                                                            String paramName) {
        TypeName tn =
                ParameterizedTypeName.get(typeMirror);

        return ParameterSpec.builder(tn, paramName).build();
    }



}
