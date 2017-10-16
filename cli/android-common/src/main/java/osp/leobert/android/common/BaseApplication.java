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

    private CacheController cacheController;

    @Override
    public void onCreate() {
        super.onCreate();
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
