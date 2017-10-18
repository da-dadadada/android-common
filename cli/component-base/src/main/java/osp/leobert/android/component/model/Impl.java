package osp.leobert.android.component.model;

import android.support.annotation.NonNull;

/**
 * <p><b>Package:</b> osp.leobert.android.component.model </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> Impl </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/17.
 */

public class Impl<T> {
    T impl;

    public Impl(@NonNull T impl) {
        this.impl = impl;
    }

    public T getImpl() {
        return impl;
    }
}
