package com.example.gypsophila.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/25.
 */
public class GalleryAty extends Activity implements AdapterView.OnItemSelectedListener,ViewSwitcher.ViewFactory{

    private int[] resId = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5, R.mipmap.item6,
            R.mipmap.item7, R.mipmap.item8, R.mipmap.item9, R.mipmap.item10, R.mipmap.item11, R.mipmap.item12};
    private Gallery gallery;
    private ImageAdapter adapter;
    private ImageSwitcher imageSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        gallery = (Gallery) findViewById(R.id.gallery);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        //设置好适配器
        adapter = new ImageAdapter(resId, this);
        //gallery加载适配器
        gallery.setAdapter(adapter);
        gallery.setOnItemSelectedListener(this);

        imageSwitcher.setFactory(this);
        //设置效果
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("gallery", "onSelected被调用");
        //加载被选中的图片
        imageSwitcher.setBackgroundResource(resId[position % resId.length]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public View makeView() {
        //日志上看被调用了两次.
        Log.i("gallery", "makeview被调用");
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }
}
