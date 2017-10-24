package osp.leobert.clidemo.router;

import osp.leobert.android.component.router.IComponentRouter;
import osp.leobert.android.router.facade.annotation.Router;

/**
 * <p><b>Package:</b> osp.leobert.clidemo.router </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> MainUiRouter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/24.
 */
@Router(host = "mainComponent",group = "main",alias = "MainCompo_Home")
public interface MainUiRouter extends IComponentRouter{
}
