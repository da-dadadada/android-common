package osp.leobert.android.common.presentation.presenter;

import osp.leobert.android.common.domain.executor.Executor;
import osp.leobert.android.common.domain.executor.MainThread;

/**
 * <p><b>Package:</b> osp.leobert.android.common.presentation </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AbstractPresenter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/16.
 */

public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}