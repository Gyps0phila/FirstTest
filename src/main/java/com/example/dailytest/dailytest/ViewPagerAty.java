package com.example.dailytest.dailytest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.weather.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/1/22.
 */
public class ViewPagerAty extends Activity {
    private ViewPager viewPager;
    //布局转化为view对象
    private List<View> viewList;
    private List<String> tabList;
    private PagerTabStrip tabStrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        viewPager = (ViewPager) findViewById(R.id.viewpager );
        viewList = new ArrayList<View>();
        View view1 = View.inflate(this, R.layout.view1, null);
        View view2 = View.inflate(this, R.layout.view2, null);
        View view3 = View.inflate(this, R.layout.view3, null);
        View view4 = View.inflate(this, R.layout.view4, null);

        //设置tab
        tabList = new ArrayList<String>();
        tabList.add("第一页");
        tabList.add("第二页");
        tabList.add("第三页");
        tabList.add("第四页");

        //设置页卡标题的样式
        tabStrip = (PagerTabStrip) findViewById(R.id.tabStrip);
        tabStrip.setBackgroundColor(Color.YELLOW);
        tabStrip.setTextColor(Color.RED);
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTabIndicatorColor(Color.GREEN);//页卡标题小粗线的颜色
        //通过view对象去作为viewpager的数据源
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        //创建viewpager的适配器
        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(viewList,tabList);

        //view加载适配器
        viewPager.setAdapter(pagerAdapter);

    }
}
