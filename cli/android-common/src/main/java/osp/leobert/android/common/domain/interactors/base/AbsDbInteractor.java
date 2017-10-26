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

package osp.leobert.android.common.domain.interactors.base;


import java.lang.ref.WeakReference;

import osp.leobert.android.common.domain.executor.impl.ThreadExecutor;
import osp.leobert.android.common.threading.MainThreadImpl;

import static osp.leobert.android.common.log.ILogger.logger;

/**
 * <p><b>Package:</b> com.lht.creationspace.base.domain.interactors </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> AbsDbInteractor </p>
 * <p><b>Description:</b> DB 任务 </p>
 * Created by leobert on 2017/8/4.
 */

public abstract class AbsDbInteractor<R> extends AbstractInteractor {

    public interface OnTaskFinishListener<R> {
        void onSuccess(R result);

        void onCanceledBeforeRun();
    }

    private WeakReference<OnTaskFinishListener<R>> listenerRef;

    public AbsDbInteractor() {
        this(null);
    }

    public AbsDbInteractor(OnTaskFinishListener<R> listener) {
        super(ThreadExecutor.getInstance(), MainThreadImpl.getInstance());
        if (listener != null) {
            listenerRef = new WeakReference<>(listener);
        }
    }

    @Override
    public void run() {
        if (mIsCanceled) {
            callbackOnCanceledBeforeRun();
            return;
        }

        R result = runTask();
        callbackOnSuccess(result);

    }

    private void callbackOnCanceledBeforeRun() {
        if (listenerRef == null) {
            logger.debug(getClass().getSimpleName(), "Job OnCanceledBeforeRun,no callback");
            return;
        }

        final OnTaskFinishListener<R> listener = listenerRef.get();

        if (listener == null) {
            logger.error(getClass().getSimpleName(), "Job OnCanceledBeforeRun,callback null ,err!");
            return;
        }

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                listener.onCanceledBeforeRun();
            }
        });
    }

    private void callbackOnSuccess(final R result) {
        if (listenerRef == null) {
            logger.debug(getClass().getSimpleName(), "Job onSuccess,no callback");
            return;
        }

        final OnTaskFinishListener<R> listener = listenerRef.get();

        if (listener == null) {
            logger.debug(getClass().getSimpleName(), "Job onSuccess,callback has bean gc. won't rollback");
            return;
        }

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(result);

            }
        });
    }

    @Override
    public final void cancel() {
        if (isRunning()) {
            // release dependency
            if (listenerRef != null)
                listenerRef.clear();
        }
        super.cancel();
    }

    protected abstract R runTask();
}
