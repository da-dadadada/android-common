package osp.leobert.android.common.domain.repository.temp;

import java.util.ArrayList;
import java.util.Collection;

import osp.leobert.android.common.domain.repository.OrmContext;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.repository.temp </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> DemoOrmContext </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/18.
 */

public class DemoOrmContext<Model> implements OrmContext<DemoOrmContext<Model>,Model> {
    @Override
    public DemoOrmContext<Model> getOrmContext() {
        return this;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public long save(Model model) {
        return 0;
    }

    @Override
    public int save(Collection collection) {
        return 0;
    }

    @Override
    public long insert(Model model) {
        return 0;
    }

    @Override
    public int insert(Collection collection) {
        return 0;
    }

    @Override
    public int update(Model model) {
        return 0;
    }

    @Override
    public int update(Collection collection) {
        return 0;
    }

    @Override
    public int delete(Model model) {
        return 0;
    }

    @Override
    public int delete(Class<Model> aClass) {
        return 0;
    }

    @Override
    public int deleteAll(Class<Model> aClass) {
        return 0;
    }

    @Override
    public int delete(Collection<Model> collection) {
        return 0;
    }

    @Override
    public ArrayList<Model> query(Class<Model> aClass) {
        return null;
    }

    @Override
    public Model queryById(long l, Class<Model> aClass) {
        return null;
    }

    @Override
    public Model queryById(String s, Class<Model> aClass) {
        return null;
    }

    @Override
    public long queryCount(Class<Model> aClass) {
        return 0;
    }

    @Override
    public boolean dropTable(Class<Model> aClass) {
        return false;
    }

    @Override
    public boolean dropTable(String s) {
        return false;
    }

    @Override
    public void close() {

    }
}
