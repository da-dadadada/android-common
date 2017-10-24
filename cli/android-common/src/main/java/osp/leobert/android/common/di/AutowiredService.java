package osp.leobert.android.common.di;

import osp.leobert.android.common.di.impl.AutowiredServiceImpl;

/**
 * <p><b>Package:</b> osp.leobert.android.common.di </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AutowiredService </p>
 * <p><b>Description:</b>  Autowired Dependency inject </p>
 * Created by leobert on 2017/9/18.
 */

public interface AutowiredService {

    /**
     * Autowired core.
     * @param instance the instance who need autowired.
     */
    void autowire(Object instance);


    class Factory {
        private static Factory instance;

        public static Factory getInstance() {
            if (instance == null) {
                instance = new Factory();
            }
            return instance;
        }

        public AutowiredService create() {
            return new AutowiredServiceImpl();
        }
    }
}
