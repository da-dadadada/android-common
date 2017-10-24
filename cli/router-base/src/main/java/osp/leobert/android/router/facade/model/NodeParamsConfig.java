package osp.leobert.android.router.facade.model;

import java.util.HashMap;

/**
 * <p><b>Package:</b> osp.leobert.android.router.facade.model </p>
 * <p><b>Project:</b> router-annotation </p>
 * <p><b>Classname:</b> NodeParamsConfig </p>
 * <p><b>Description:</b> bean for Node params' type </p>
 * Created by leobert on 2017/9/29.
 */

public class NodeParamsConfig {
    private HashMap<String,Integer> paramsType = new HashMap<>();

    public void add(String name,Integer type) {
        paramsType.put(name, type);
    }

    public Integer getType(String name) {
        return paramsType.get(name);
    }

    public boolean contains(String name) {
        return paramsType.containsKey(name);
    }
}
