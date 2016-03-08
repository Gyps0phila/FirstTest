package com.example.gypsophila.baidumap;

import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.gypsophila.dailytest.BaseAty;
import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/8.
 * 新建jniLibs！！！so文件放在libs中是没有用的，studio配置
 */
public class BaiduMapAty extends BaseAty {


    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在加载布局前应该初始化地图
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.baidumap);
        mapView = (MapView) findViewById(R.id.mapView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
