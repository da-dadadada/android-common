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
