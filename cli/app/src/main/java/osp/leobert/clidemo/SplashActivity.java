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

package osp.leobert.clidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import osp.leobert.android.common.domain.executor.MainThread;
import osp.leobert.android.common.threading.MainThreadImpl;
import osp.leobert.android.component.router.UiActivityUri;
import osp.leobert.android.component.router.UiRouter;
import osp.leobert.android.router.facade.annotation.RouteNode;
import osp.leobert.android.router.facade.annotation.Router;
import osp.leobert.clidemo.router.$$PlaceHolder;

@RouteNode(path = "/main/splash",group = "main")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MainThread mainThread = MainThreadImpl.getInstance();
        mainThread.postDelayed(new Runnable() {
            @Override
            public void run() {
                UiRouter.getInstance()
                        .openUri(SplashActivity.this,
                                new UiActivityUri($$PlaceHolder.MainCompo_Home.UI_MAIN_INDEX),null);
            }
        },2000);
    }
}
