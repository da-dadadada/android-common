package osp.leobert.android.common.domain.bean;

import android.support.annotation.NonNull;

import static osp.leobert.android.common.log.ILogger.logger;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.bean </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> CurrentUser </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/16.
 */

public final class CurrentUser {

    private static CurrentUser Instance;

    public static void init(@NonNull UserInfo userInfo) {
        Instance = new CurrentUser(userInfo);
    }

    public static CurrentUser getInstance() {
        if (Instance == null) {
            logger.error("CurrentUser", "user not initialized");
            throw new IllegalStateException("user not initialized");
        }
        return Instance;
    }

    private UserInfo userInfo;

    public CurrentUser(@NonNull UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean hasLogin() {
        return userInfo.hasLogin();
    }

    public String getUsername() {
        return userInfo.getUsername();
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void updateUserInfo(@NonNull UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
