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
 * <p><b>Description:</b>
 * define a class to implement this interface as follow:
 * <p>
 * public class DemoOrmContext&lt;Model&gt; implements OrmContext&lt;DemoOrmContext&lt;Model&gt;,Model&gt;
 * <p>
 * maybe LiteOrmContext with LiteOrm or OrmLiteContext with OrmLite
 * <p>
 * Created by leobert on 2017/10/18.
 */

public interface OrmContext<O extends OrmContext<O, Model>, Model> {

    int SINGLE = 1;

    int CASCADE = 2;

    O getOrmContext();


    @IntDef({SINGLE, CASCADE})
    @Retention(RetentionPolicy.SOURCE)
    @interface InstanceType {
    }

    boolean isConnected();


    long save(Model model);

    int save(Collection<Model> collection);

    long insert(Model model);

    int insert(Collection collection);

    int update(Model model);

    int update(Collection<Model> collection);

    int delete(Model model);

    int delete(Class<Model> aClass);

    int deleteAll(Class<Model> aClass);

    int delete(Collection<Model> collection);

    ArrayList<Model> query(Class<Model> aClass);

    Model queryById(long l, Class<Model> aClass);

    Model queryById(String s, Class<Model> aClass);

    long queryCount(Class<Model> aClass);

    boolean dropTable(Class<Model> aClass);

    void close();

}
