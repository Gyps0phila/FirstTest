package com.example.dailytest.dailytest;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Gypsophila on 2016/1/22.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<String> tabList;
    public MyViewPagerAdapter(List<View> viewList,List<String> tabList) {
        this.viewList = viewList;
        this.tabList = tabList;
    }
    //返回页卡的数量
    @Override
    public int getCount() {
        return viewList.size();
    }


    //是否是view对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //实例化页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //加载页卡并且返回
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
    //销毁页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //移除页卡
        container.removeView(viewList.get(position));
    }

    /**
     * 设置页卡的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
