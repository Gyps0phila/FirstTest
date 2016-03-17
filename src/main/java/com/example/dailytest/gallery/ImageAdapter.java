package com.example.dailytest.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Gypsophila on 2016/1/25.
 */
public class ImageAdapter extends BaseAdapter {

    private int[] resId;
    private Context context;
    public ImageAdapter(int[] resId,Context context) {
        this.resId = resId;
        this.context = context;
    }

    @Override
    public int getCount() {
//        return resId.length;
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return resId[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //被调用多次
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //取余方式制造循环.
        ImageView imageView = new ImageView(context);
//        imageView.setBackgroundResource(resId[position]);
        imageView.setBackgroundResource(resId[position%resId.length]);

        imageView.setLayoutParams(new Gallery.LayoutParams(400, 300));
        //缩放模式
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
