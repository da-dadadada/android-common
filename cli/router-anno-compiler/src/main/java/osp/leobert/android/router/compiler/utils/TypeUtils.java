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

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import osp.leobert.android.router.compiler.Constants;
import osp.leobert.android.router.facade.enums.Type;

import static osp.leobert.android.router.compiler.Constants.PARCELABLE;

/**
 * <p><b>Package:</b> osp.leobert.android.router.compiler.utils </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> TypeUtils </p>
 * <p><b>Description:</b> utils for type inference </p>
 * Created by leobert on 2017/10/23.
 */

public class TypeUtils {
    private Types types;
    private Elements elements;
    private TypeMirror parcelableType;

    public TypeUtils(Types types, Elements elements) {
        this.types = types;
        this.elements = elements;

        parcelableType = this.elements.getTypeElement(PARCELABLE).asType();
    }

    /**
     * Diagnostics out the true java type
     *
     * @param element Raw type
     * @return Type class of java
     */
    public int typeExchange(Element element) {
        TypeMirror typeMirror = element.asType();

        // Primitive
        if (typeMirror.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }

        switch (typeMirror.toString()) {
            case Constants.BYTE:
                return Type.BYTE.ordinal();
            case Constants.SHORT:
                return Type.SHORT.ordinal();
            case Constants.INTEGER:
                return Type.INT.ordinal();
            case Constants.LONG:
                return Type.LONG.ordinal();
            case Constants.FLOAT:
                return Type.FLOAT.ordinal();
            case Constants.DOUBEL:
                return Type.DOUBLE.ordinal();
            case Constants.BOOLEAN:
                return Type.BOOLEAN.ordinal();
            case Constants.STRING:
                return Type.STRING.ordinal();
            default:    // Other side, maybe the PARCELABLE or OBJECT.
                if (types.isSubtype(typeMirror, parcelableType)) {  // PARCELABLE
                    return Type.PARCELABLE.ordinal();
                } else {    // For others
                    return Type.OBJECT.ordinal();
                }
        }
    }
}
