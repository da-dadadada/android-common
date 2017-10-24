package osp.leobert.clidemo.router;


import osp.leobert.android.router.facade.annotation.UiRoutersHolder;

/**
 * <p><b>Package:</b> com.mrzhang.componentservice.address </p>
 * <p><b>Project:</b> DDComponentForAndroid </p>
 * <p><b>Classname:</b> PlaceHolder </p>
 * <p><b>Description:</b>
 *
 * <em> never do anything there!</em>
 *
 * <em>attention it cannot cross module!</em>
 *
 * all UiActivity defined a route will generate an
 * {@link osp.leobert.android.component.router.Address}
 * instance here, we will group them by 'component' and 'group'; </p>
 * when use {@link osp.leobert.android.router.facade.annotation.Router},
 * we defined a group and a host associated with it's component,
 * e.g. '@Router(host = "shareComponent",group = "share")'
 * we will get a inner class ShareComponent_Share, not graceful yeh? we can also use
 * {@link osp.leobert.android.router.facade.annotation.Router#alias()} to name an alias, which will be used as the class name.
 * <p>
 * Then, we will define an instance of Address named by the info given in the
 * {@link osp.leobert.android.router.facade.annotation.RouteNode}<p>
 * e.g. in ShareComponent '@RouteNode(path = "/share",group = "share")'
 * will get UI_SHARE, if the path is sth like:"/foo/bar", will get UI_FOO_BAR
 * <p>
 * Created by leobert on 2017/9/28.
 */
@UiRoutersHolder
public final class PlaceHolder {
}
