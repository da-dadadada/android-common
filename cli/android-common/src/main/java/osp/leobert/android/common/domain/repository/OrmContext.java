package osp.leobert.android.common.domain.repository;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.repository </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> OrmContext </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/18.
 */

public interface OrmContext<O extends OrmContext> {

    int SINGLE = 1;

    int CASCADE = 2;

    O getOrmContext();


    @IntDef({SINGLE, CASCADE})
    @Retention(RetentionPolicy.SOURCE)
    @interface InstanceType {
    }

    boolean isConnected();


    <Model> long save(Model model);

    <Model> int save(Collection collection);

    <Model> long insert(Model model);

    int insert(Collection collection);

    <Model> int update(Model model);

    int update(Collection collection);

    <Model> int delete(Model model);

    <Model> int delete(Class<Model> aClass);

    <Model> int deleteAll(Class<Model> aClass);

    <Model> int delete(Collection<Model> collection);

    <Model> ArrayList<Model> query(Class<Model> aClass);

    <Model> Model queryById(long l, Class<Model> aClass);

    <Model> Model queryById(String s, Class<Model> aClass);

    <Model> long queryCount(Class<Model> aClass);

    <Model> boolean dropTable(Class<Model> aClass);

    boolean dropTable(String s);

    void close();

}
