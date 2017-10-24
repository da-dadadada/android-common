package osp.leobert.android.common.di.impl;

import android.util.LruCache;

import java.util.ArrayList;
import java.util.List;

import osp.leobert.android.common.di.AutowiredService;
import osp.leobert.android.common.di.ISyringe;

/**
 * <p><b>Package:</b> osp.leobert.android.common.di.impl </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AutowiredServiceImpl </p>
 * <p><b>Description:</b> implement of {@link AutowiredService},used to fetch
 * data from bundles in the intent</p>
 * Created by leobert on 2017/9/18.
 */

public class AutowiredServiceImpl implements AutowiredService {

    private LruCache<String, ISyringe> classCache = new LruCache<>(50);
    private List<String> blackList = new ArrayList<>();

    //attention! make sure this keeps same with the one in AutowiredProcessor
    private static final String SUFFIX_AUTOWIRED = "$$Router$$Autowired";

    @Override
    public void autowire(Object instance) {
        String className = instance.getClass().getName();
        try {
            if (!blackList.contains(className)) {
                ISyringe autowiredHelper = classCache.get(className);
                if (null == autowiredHelper) {  // No cache.
                    autowiredHelper = (ISyringe) Class.forName(instance.getClass().getName() + SUFFIX_AUTOWIRED)
                            .getConstructor().newInstance();
                }
                autowiredHelper.inject(instance);
                classCache.put(className, autowiredHelper);
            }
        } catch (Exception ex) {
            blackList.add(className);    // This instance need not autowired.
        }
    }
}
