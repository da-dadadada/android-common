package osp.leobert.clidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import osp.leobert.android.router.facade.annotation.RouteNode;
import osp.leobert.android.router.facade.annotation.Router;

@RouteNode(path = "/main/index",group = "main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Address
    }
}
