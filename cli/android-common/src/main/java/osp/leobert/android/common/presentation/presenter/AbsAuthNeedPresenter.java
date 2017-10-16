package osp.leobert.android.common.presentation.presenter;

import osp.leobert.android.common.domain.bean.CurrentUser;
import osp.leobert.android.common.domain.event.LoginCanceledEvent;
import osp.leobert.android.common.domain.event.LoginSuccessEvent;
import osp.leobert.android.common.domain.executor.Executor;
import osp.leobert.android.common.domain.executor.MainThread;
import osp.leobert.android.common.domain.trigger.Trigger;

import static osp.leobert.android.common.log.ILogger.logger;

/**
 * <p><b>Package:</b> osp.leobert.android.common.presentation.presenter </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AbsAuthNeedPresenter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/16.
 */

public abstract class AbsAuthNeedPresenter extends AbstractPresenter {
    public AbsAuthNeedPresenter(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }


    protected abstract <T> boolean dispatchLoginSuccess(Trigger<T> trigger);

    /**
     * desc: 登录可能被用户手动取消，此时发出LoginCancelEvent，订阅的页面需要特殊处理<p>
     * 并不是所有的登录相关页面都需要处理该逻辑，所以#ABSVerifyNeedPresenter中进行空实现
     *
     * @param trigger an interface to identify trigger,use equal(ITriggerCompare compare)
     */
    protected <T> boolean dispatchLoginCanceled(Trigger<T> trigger) {
        return false;
    }

    public final void onLoginSuccess(LoginSuccessEvent event) {
        boolean hasAte = dispatchLoginSuccess(event.getTrigger());
        if (!hasAte)
            logger.debug("onLoginSuccess", "no one has ate this event:" + event.getTrigger().getClass().getSimpleName());
    }

    public final void onLoginCanceled(LoginCanceledEvent event) {
        boolean hasAte = dispatchLoginCanceled(event.getTrigger());
        if (!hasAte)
            logger.debug("onLoginCanceled", "no one has ate this event:" + event.getTrigger().getClass().getSimpleName());
    }

    /**
     * desc: check if login
     *
     * @return true while login,false otherwise
     */
    protected final boolean hasLogin() {
        return CurrentUser.getInstance().hasLogin();
    }
}
