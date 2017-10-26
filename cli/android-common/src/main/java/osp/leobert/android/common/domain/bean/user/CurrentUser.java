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

package osp.leobert.android.common.domain.bean.user;

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
