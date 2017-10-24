package osp.leobert.android.component.router;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import osp.leobert.android.router.facade.Constants;

/**
 * Singleton implement of {@link IUIRouter}
 * provides services for UI-Component
 */

public class UiRouter implements IUIRouter {
    private static Map<String, IComponentRouter> routerInstanceCache = new HashMap<>();

    private List<IComponentRouter> uiRouters = new ArrayList<>();
    private HashMap<IComponentRouter, Integer> priorities = new HashMap<>();

    private static volatile UiRouter instance;

    private UiRouter() {
    }

    public static UiRouter getInstance() {
        if (instance == null) {
            synchronized (UiRouter.class) {
                if (instance == null) {
                    instance = new UiRouter();
                }
            }
        }
        return instance;
    }


    @Override
    public void registerUI(IComponentRouter router, int priority) {
        if (priorities.containsKey(router) && priority == priorities.get(router)) {
            return;
        }
        removeOldUIRouter(router);
        int i = 0;
        for (IComponentRouter temp : uiRouters) {
            Integer tp = priorities.get(temp);
            if (tp == null || tp <= priority) {
                break;
            }
            i++;
        }
        uiRouters.add(i, router);
        priorities.put(router, priority);
    }

    @Override
    public void registerUI(IComponentRouter router) {
        registerUI(router, PRIORITY_NORMAL);
    }

    @Override
    public void unregisterUI(IComponentRouter router) {
        for (int i = 0; i < uiRouters.size(); i++) {
            if (router == uiRouters.get(i)) {
                uiRouters.remove(i);
                priorities.remove(router);
                break;
            }
        }
    }

    @Override
    public boolean openUri(Context context, String url, Bundle bundle) {
        url = url.trim();
        if (!TextUtils.isEmpty(url)) {
            if (!url.contains("://") &&
                    (!url.startsWith("tel:") ||
                            !url.startsWith("smsto:") ||
                            !url.startsWith("file:"))) {
                url = "http://" + url;
            }
            Uri uri = Uri.parse(url);
            return openUri(context, uri, bundle);
        }
        return true;
    }

    public boolean openUri(Context context, UiActivityUri uri, Bundle bundle) {
        if (uri != null)
            return openUri(context, uri.getUri(), bundle);
        return true;
    }

    @Override
    public boolean openUri(Context context, Uri uri, Bundle bundle) {
        for (IComponentRouter temp : uiRouters) {
            try {
                if (temp.verifyUri(uri) && temp.openUri(context, uri, bundle)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean verifyUri(Uri uri) {
        for (IComponentRouter temp : uiRouters) {
            if (temp.verifyUri(uri)) {
                return true;
            }
        }
        return false;
    }


    private void removeOldUIRouter(IComponentRouter router) {
        Iterator<IComponentRouter> iterator = uiRouters.iterator();
        while (iterator.hasNext()) {
            IComponentRouter tmp = iterator.next();
            if (tmp == router) {
                iterator.remove();
                priorities.remove(tmp);
            }
        }
    }

    public static IComponentRouter fetch(@NonNull Class<? extends IComponentRouter> clz) {
        if (!clz.isInterface())
            throw new IllegalArgumentException("need a interface, but this isn't a interface:" + clz.getName());

        osp.leobert.android.router.facade.annotation.Router router =
                clz.getAnnotation(osp.leobert.android.router.facade.annotation.Router.class);
        if (router == null)
            throw new IllegalArgumentException("not annotated with Router:" + clz.getName());

        String path = Constants.ROUTERIMPL_OUTPUT_PKG +
                Constants.DOT + router.group() + Constants.DOT + clz.getSimpleName() + "Impl";
        if (routerInstanceCache.containsKey(path))
            return routerInstanceCache.get(path);

        try {
            Class cla = Class.forName(path);
            IComponentRouter instance = (IComponentRouter) cla.newInstance();
            routerInstanceCache.put(path, instance);
            return instance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
