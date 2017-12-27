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

package osp.leobert.android.router.parser;

import android.net.Uri;
import android.os.Bundle;

import java.util.Set;

import osp.leobert.android.router.facade.enums.Type;
import osp.leobert.android.router.facade.model.NodeParamsConfig;

/**
 * <p><b>Package:</b> osp.leobert.android.router.parser </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> QsBundleParser </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/1.
 */

public class QsBundleParser implements QsParamParser<Bundle,Uri> {
    @Override
    public Bundle parse(Bundle bundle, Uri uri, NodeParamsConfig config) {
        if (bundle == null && uri.getQuery() != null && uri.getQuery().length() > 0) {
            bundle = new Bundle();

            Set<String> paramKeys = uri.getQueryParameterNames();
            for (String key : paramKeys) {
                if (config.contains(key)) {
                    Integer type = config.getType(key);

                    if (type == Type.BOOLEAN.ordinal()) {
                        bundle.putBoolean(key, Boolean.valueOf(uri.getQueryParameter(key)));
                    } else if (type == Type.BYTE.ordinal()) {
                        bundle.putByte(key, Byte.valueOf(uri.getQueryParameter(key)));
                    } else if (type == Type.SHORT.ordinal()) {
                        bundle.putShort(key, Short.valueOf(uri.getQueryParameter(key)));
                    } else if (type == Type.INT.ordinal()) {
                        bundle.putInt(key, Integer.valueOf(uri.getQueryParameter(key)));
                    } else if (type == Type.CHAR.ordinal()) {
                        bundle.putChar(key, uri.getQueryParameter(key).charAt(0));
                    } else if (type == Type.LONG.ordinal()) {
                        bundle.putLong(key, Long.valueOf(uri.getQueryParameter(key)));
                    } else if (type == Type.FLOAT.ordinal()) {
                        bundle.putFloat(key, Float.valueOf(uri.getQueryParameter(key)));
                    } else if (type == Type.DOUBLE.ordinal()) {
                        bundle.putDouble(key, Double.valueOf(uri.getQueryParameter(key)));
                    } else {
                        //  STRING,
                        //  PARCELABLE, cannot encode into a url
                        //  OBJECT
                        bundle.putString(key, uri.getQueryParameter(key));
                    }
                }
            }
            //continue
        }
        return bundle;
    }
}
