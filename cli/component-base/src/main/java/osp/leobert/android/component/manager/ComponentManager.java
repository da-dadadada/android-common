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
