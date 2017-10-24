package osp.leobert.android.router.compiler;

import osp.leobert.android.router.facade.annotation.Autowired;
import osp.leobert.android.router.facade.annotation.RouteNode;
import osp.leobert.android.router.facade.annotation.Router;
import osp.leobert.android.router.facade.annotation.UiRoutersHolder;

/**
 * <p><b>Package:</b> osp.leobert.android.router.compiler </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> Constants </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/23.
 */

public interface Constants {

    String PREFIX_OF_LOGGER = "[Router-Anno-Compiler]-- ";

    String KEY_MODULE_NAME = "ModuleName";

    // System interface
    String ACTIVITY = "android.app.Activity";
    String FRAGMENT = "android.app.Fragment";
    String FRAGMENT_V4 = "android.support.v4.app.Fragment";
    String SERVICE = "android.app.Service";
    String PARCELABLE = "android.os.Parcelable";

    // Java type
    String LANG = "java.lang";
    String BYTE = LANG + ".Byte";
    String SHORT = LANG + ".Short";
    String INTEGER = LANG + ".Integer";
    String LONG = LANG + ".Long";
    String FLOAT = LANG + ".Float";
    String DOUBEL = LANG + ".Double";
    String BOOLEAN = LANG + ".Boolean";
    String STRING = LANG + ".String";

    String ANNOTATION_TYPE_AUTOWIRED = Autowired.class.getName();
    String ANNOTATION_TYPE_ROUTE_NODE = RouteNode.class.getName();
    String ANNOTATION_TYPE_ROUTER = Router.class.getName();
    String ANNOTATION_TYPE_UIROUTERSHOLDER = UiRoutersHolder.class.getName();

}
