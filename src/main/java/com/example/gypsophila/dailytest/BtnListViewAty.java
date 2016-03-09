package com.example.gypsophila.dailytest;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/3/9.
 */
public class BtnListViewAty extends BaseAty {

    private ListView funcListView;
    private List<FuncBean> funcList;
    private FuncAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_listview);

        init();
        funcListView.setAdapter(adapter);
    }

    private void init() {
        funcListView = (ListView) findViewById(R.id.func_listview);
        funcList = new ArrayList<>();
        //成功！
//        FuncBean bean1 = new FuncBean();
//        bean1.setResId(R.mipmap.item2);
//        bean1.setFuncDescription("com.example.gypsophila.baidumap.BaiduMapAty");
//        funcList.add(bean1);
//        for (int i = 0; i < 20; i++) {
//            FuncBean funcBean = new FuncBean();
//            funcBean.setResId(R.mipmap.ic_launcher);
//            funcBean.setFuncDescription("it is fun" + i);
//            funcList.add(funcBean);
//        }
        FuncBean bean1 = new FuncBean();
        bean1.setResId(R.mipmap.item10);
        bean1.setFuncDescription("com.example.gypsophila.sensor.SensorAty");
        funcList.add(bean1);
        adapter = new FuncAdapter(this, funcList);
    }

}
