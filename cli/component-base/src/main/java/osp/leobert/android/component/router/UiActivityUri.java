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

package osp.leobert.android.component.router;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import osp.leobert.android.router.facade.model.Address;

/**
 * <p><b>Package:</b> com.mrzhang.component.componentlib.router </p>
 * <p><b>Project:</b> router-annotation </p>
 * <p><b>Classname:</b> Path </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/9/23.
 */

public final class UiActivityUri {

    private final String host;

    private final String path;

    private String queryString;

    public UiActivityUri(@NonNull String host, @NonNull String path) {
        this.host = host;
        this.path = path;
    }

    public UiActivityUri(@NonNull Address address) {
        this(address.getHost(),address.getPath());
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    private boolean needReformat = false;

    public void setQueryString(String queryString) {
        needReformat = true;
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }

    private Uri uri;

    public Uri getUri() {
        if (uri == null || needReformat) {
            String url = "https://"+getHost()+getPath() +
                    (TextUtils.isEmpty(queryString)?"":"?"+queryString);
           uri = Uri.parse(url);
        }
        return uri;
    }
}
