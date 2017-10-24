package osp.leobert.android.router.compiler.model;

import static osp.leobert.android.router.facade.Utils.checkNullOrEmpty;
import static osp.leobert.android.router.facade.Utils.isNullOrEmpty;

/**
 * <p><b>Package:</b> osp.leobert.android.router.compiler.model </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> GroupInfo </p>
 * <p><b>Description:</b> bean to hold info of {@link osp.leobert.android.router.facade.annotation.Router} </p>
 *
 * Created by leobert on 2017/10/23.
 */

public class GroupInfo {
    private String host;
    private String outPutPath;
    private String interfacePath;
    private String group;
    private String alias;

    public GroupInfo(String host, String group, String alias, String outPutPath, String interfacePath) {
        checkNullOrEmpty(host, "host");
        checkNullOrEmpty(group, "group");
        this.host = host;
        this.group = group;
        this.outPutPath = outPutPath;
        this.interfacePath = interfacePath;
        if (isNullOrEmpty(alias)) {
            this.alias = host + "_" + group;
        } else {
            this.alias = alias;
        }
    }

    public String getHost() {
        return host;
    }

    public String getOutPutPath() {
        return outPutPath;
    }

    public String getInterfacePath() {
        return interfacePath;
    }

    public String getGroup() {
        return group;
    }

    public String getAlias() {
        return alias;
    }
}
