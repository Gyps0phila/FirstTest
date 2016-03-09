package com.example.gypsophila.baidumap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.example.gypsophila.dailytest.BaseAty;
import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/8.
 * 新建jniLibs！！！so文件放在libs中是没有用的，studio配置
 */
public class BaiduMapAty extends BaseAty {


    private MapView mapView;
    private BaiduMap mBaiduMap;



    //如何在非MainActivity中加入右键菜单项
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在加载布局前应该初始化地图
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.baidumap);
        initView();
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mapView.getMap();
        //设置比例为500m
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mapmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_map_common: {
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            }
            case R.id.id_map_site: {
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            }
            case R.id.id_map_traffic: {
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通(off)");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通(on)");
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
