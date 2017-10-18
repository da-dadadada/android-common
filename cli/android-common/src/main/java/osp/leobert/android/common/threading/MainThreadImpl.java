package osp.leobert.android.common.threading;

import android.os.Handler;
import android.os.Looper;

import osp.leobert.android.common.domain.executor.MainThread;

/**
 * <p><b>Package:</b> osp.leobert.android.common.threading </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> MainThreadImpl </p>
 * <p><b>Description:</b> MainThreadImpl,post jobs to main thread </p>
 * Created by leobert on 2017/10/16.
 */

public class MainThreadImpl implements MainThread{
    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}
