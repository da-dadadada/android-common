package osp.leobert.android.common.domain.event;

import osp.leobert.android.common.domain.bean.UserInfo;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.event </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> LoginSuccessEvent </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/16.
 */

public class LoginSuccessEvent<T extends UserInfo> extends TriggerHolderEvent {

    private T userInfo;

    public LoginSuccessEvent(T userInfo) {
        this.userInfo = userInfo;
    }

    public T getUserInfo() {
        return userInfo;
    }
}
