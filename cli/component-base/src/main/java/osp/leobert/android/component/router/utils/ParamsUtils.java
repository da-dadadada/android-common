package osp.leobert.android.component.router.utils;

import android.net.Uri;
import android.os.Bundle;


import java.util.Set;

import osp.leobert.android.router.facade.enums.Type;
import osp.leobert.android.router.facade.model.NodeParamsConfig;

/**
 * ParamsUtils
 * <p>
 * Created by leobert on 2017/9/29.
 */

public class ParamsUtils {

    public static Bundle parseIfNeed(Bundle bundle, Uri uri, NodeParamsConfig config) {
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
