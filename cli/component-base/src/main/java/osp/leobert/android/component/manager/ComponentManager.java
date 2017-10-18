package osp.leobert.android.component.manager;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

import osp.leobert.android.component.AbsComponent;
import osp.leobert.android.component.model.Impl;

/**
 * <p><b>Package:</b> osp.leobert.android.component.manager </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> ComponentManager </p>
 * <p><b>Description:</b> used to manage component and service provided by component.  </p>
 * Created by leobert on 2017/10/17.
 */
@SuppressWarnings("unused")
public class ComponentManager {

    private HashMap<String, Impl> services = new HashMap<>();

    private static HashMap<String, AbsComponent> components = new HashMap<>();

    private static volatile ComponentManager instance;

    private ComponentManager() {
    }

    public static ComponentManager getInstance() {
        if (instance == null) {
            synchronized (ComponentManager.class) {
                if (instance == null) {
                    instance = new ComponentManager();
                }
            }
        }
        return instance;
    }


    public synchronized <T, I extends T> void addService(Class<T> componentServiceClass, I serviceImpl) {
        if (componentServiceClass == null || serviceImpl == null) {
            return;
        }
        services.put(componentServiceClass.getName(), new Impl<>(serviceImpl));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public synchronized <T> T getService(Class<T> componentServiceClass) {
        if (componentServiceClass == null) {
            return null;
        }
        //it is safety, we check it when add;
        Impl<T> impl = services.get(componentServiceClass.getName());
        return impl.getImpl();
    }

    public synchronized void removeService(Class serviceName) {
        if (serviceName == null) {
            return;
        }
        services.remove(serviceName.getName());
    }

    public static void registerComponent(@Nullable String classname) {
        if (TextUtils.isEmpty(classname)) {
            return;
        }
        if (components.keySet().contains(classname)) {
            return;
        }
        try {
            Class clazz = Class.forName(classname);
            AbsComponent component = (AbsComponent) clazz.newInstance();
            component.onLoad();
            components.put(classname, component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterComponent(@Nullable String classname) {
        if (TextUtils.isEmpty(classname)) {
            return;
        }
        if (components.keySet().contains(classname)) {
            components.get(classname).onRemove();
            components.remove(classname);
            return;
        }

        //following codes seem like useless
        try {
            Class clazz = Class.forName(classname);
            AbsComponent component = (AbsComponent) clazz.newInstance();
            component.onRemove();
            components.remove(classname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
