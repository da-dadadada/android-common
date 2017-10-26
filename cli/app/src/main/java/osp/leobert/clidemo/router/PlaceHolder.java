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
