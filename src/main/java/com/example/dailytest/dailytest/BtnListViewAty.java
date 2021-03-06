package com.example.dailytest.dailytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weather.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/3/9.
 * 上下文菜单常被设置于listview和gridview中
 * 1.给view注册上下文菜单registerForContextMenu()
 * 2.添加上下文菜单内容onCreateContextMenu() 1)代码动态添加 2)xml添加
 * 3.设置菜单点击后响应事件
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

        funcListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FuncBean bean = funcList.get(position);
                try {
                    startActivity(new Intent(BtnListViewAty.this,Class.forName(bean.getFuncDescription())));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
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
        bean1.setFuncDescription("SensorAty");
        funcList.add(bean1);

        FuncBean bean2 = new FuncBean();
        bean2.setResId(R.mipmap.item1);
        bean2.setFuncDescription("AnimationAty");
        funcList.add(bean2);

        FuncBean bean3 = new FuncBean();
        bean3.setResId(R.mipmap.item2);
        bean3.setFuncDescription("TitleAty1");
        funcList.add(bean3);
        adapter = new FuncAdapter(this, funcList);

        FuncBean bean4 = new FuncBean();
        bean4.setResId(R.mipmap.item11);
        bean4.setFuncDescription("MsgAty");
        funcList.add(bean4);

        FuncBean bean5 = new FuncBean();
        bean5.setResId(R.mipmap.item9);
        bean5.setFuncDescription("NewsAty");
        funcList.add(bean5);

        FuncBean bean6 = new FuncBean();
        bean6.setResId(R.mipmap.item1);
        bean6.setFuncDescription("NotificationAty2");
        funcList.add(bean6);

        FuncBean bean7 = new FuncBean();
        bean7.setResId(R.mipmap.item1);
        bean7.setFuncDescription("SMS_ReceivedAty");
        funcList.add(bean7);

        FuncBean bean8 = new FuncBean();
        bean8.setResId(R.mipmap.item10);
        bean8.setFuncDescription("TakePhotoAty");
        funcList.add(bean8);

        FuncBean bean9 = new FuncBean();
        bean9.setResId(R.mipmap.item11);
        bean9.setFuncDescription("MediaPlayerAty");
        funcList.add(bean9);

        FuncBean bean10 = new FuncBean();
        bean10.setResId(R.mipmap.item11);
        bean10.setFuncDescription("VideoPlayerAty");
        funcList.add(bean10);

        FuncBean bean11 = new FuncBean();
        bean11.setResId(R.mipmap.item11);
        bean11.setFuncDescription("MyIntentServiceAty");
        funcList.add(bean11);

        FuncBean bean12 = new FuncBean();
        bean12.setResId(R.mipmap.item11);
        bean12.setFuncDescription("LongRunningServiceAty");
        funcList.add(bean12);

        FuncBean bean13 = new FuncBean();
        bean13.setResId(R.mipmap.item11);
        bean13.setFuncDescription("com.example.dailytest.intent.ObjectTransportAty1");
        funcList.add(bean13);

        //上下文菜单长按，点击区域如果是textview似乎没有反映
        // 由于在textview这边设置了点击事件监听，导致点击onItemClickListener点中文本时无效！
        this.registerForContextMenu(funcListView);

       /* ArrayList<FuncBean> i1 = new ArrayList<>();
        FuncBean bean3 = new FuncBean();
        bean3.setResId(R.mipmap.item10);
        bean3.setFuncDescription("com.example.gypsophila.sensor.SensorAty1");
        FuncBean bean4 = new FuncBean();
        bean4.setResId(R.mipmap.item10);
        bean4.setFuncDescription("com.example.gypsophila.sensor.SensorAty2");
        FuncBean bean5 = new FuncBean();
        bean5.setResId(R.mipmap.item10);
        bean5.setFuncDescription("com.example.gypsophila.sensor.SensorAty3");
        i1.add(bean3);
        i1.add(bean4);
        i1.add(bean5);
        Log.i("shadow clone", "i1 = " + i1.get(1).getFuncDescription());
        ArrayList<FuncBean> i2 = (ArrayList<FuncBean>) i1.clone();
        FuncBean funcBean = new FuncBean();
        funcBean.setResId(R.mipmap.item1);
        funcBean.setFuncDescription("hhee");
//        i2.set(1, funcBean);
        i2.get(1).setFuncDescription("hwwwww");
        Log.i("shadow clone", "i2 = " + i2.get(1).getFuncDescription());
        Log.i("shadow clone", "i1 = " + i1.get(1).getFuncDescription());*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //设置menu显示内容
        menu.setHeaderTitle("操作");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.add(1, 1, 1, "复制");
        menu.add(1, 2, 1, "粘贴");
        menu.add(1, 3, 1, "删除");

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1: {
                Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
                break;
            }
            case 2: {
                Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
                break;
            }
            case 3: {
                Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

}
