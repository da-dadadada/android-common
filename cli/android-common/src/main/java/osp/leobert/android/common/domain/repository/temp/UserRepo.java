package osp.leobert.android.common.domain.repository.temp;

import android.support.annotation.NonNull;

import osp.leobert.android.common.domain.bean.user.UserInfo;
import osp.leobert.android.common.domain.repository.BaseRepository;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.repository.temp </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> UserRepo </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/18.
 */

public class UserRepo extends BaseRepository<DemoOrmContext,UserInfo> {
    public UserRepo() {
        super(UserInfo.class);
    }

    @Override
    protected String getDatabaseName() {
        return "user_info";
    }

    @NonNull
    @Override
    protected DemoOrmContext initOrmContextAndConnect() {
        //...

//        UserInfo userInfo = t(UserInfo.class);

        return null;
    }

//    <T> T t(Class<T> c) {
//        return null;
//    }

}
