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

package osp.leobert.android.common;

import android.app.Application;
import android.support.annotation.NonNull;

import java.io.File;

import osp.leobert.android.common.module.cache.CacheController;
import osp.leobert.android.common.module.cache.ICacheController;

/**
 * <p><b>Package:</b> osp.leobert.android.common </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> BaseApplication </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/16.
 */

public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    private CacheController cacheController;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDebugger();
        initUser();

        cacheController = new CacheController(getLocalStorageRoot());
    }

    protected abstract void initDebugger();

    protected abstract void initUser();

    private String getLocalStorageRoot() {
        return getCacheDir().getPath();
    }

    public final void addCacheChangedListener(@NonNull ICacheController.OnCacheChangedListener listener) {
        cacheController.addCacheChangedListener(listener);
    }

    /**
     * 获取系统相册的位置
     */
    public synchronized File getSystemImageDir() {
        return cacheController.getSystemImageDir();
    }

    public synchronized File getLocalDownloadCacheDir() {
        return cacheController.getLocalDownloadCacheDir();
    }

    public synchronized File getSystemDownloadDir() {
        return cacheController.getSystemDownloadDir();
    }

    public File getLocalThumbnailCacheDir() {
        return cacheController.getLocalThumbnailCacheDir();
    }

    public File getLocalPreviewCacheDir() {
        return cacheController.getLocalThumbnailCacheDir();
    }
}
