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
