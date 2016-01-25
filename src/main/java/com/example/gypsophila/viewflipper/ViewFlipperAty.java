package com.example.gypsophila.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/24.
 */
public class ViewFlipperAty extends Activity {

    private ViewFlipper viewFlipper;
    private float startX;
    private int[] resId = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        //d动态导入方式为viewFlipper添加子view
        for(int i=0;i<resId.length;i++) {
            viewFlipper.addView(getImageView(resId[i]));
        }
//        //设置viewFlipper效果
//        viewFlipper.setInAnimation(this, R.anim.left_in);
//        viewFlipper.setOutAnimation(this, R.anim.left_out);
//        //设置视图切换间隔
//        viewFlipper.setFlipInterval(3000);
//        //开始播放
//        viewFlipper.startFlipping();

        //通过手势滑动viewflipper

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                break;

            }

            case MotionEvent.ACTION_UP: {
                //向左滑动，切换下一张
                if(startX-event.getX()>100) {
                    viewFlipper.setInAnimation(this, R.anim.right_in);
                    viewFlipper.setOutAnimation(this, R.anim.right_out);
                    viewFlipper.showNext();
                }
                //向右滑动，翻动上一页
                else if(event.getX()-startX>100) {
                    viewFlipper.setInAnimation(this, R.anim.left_in);
                    viewFlipper.setOutAnimation(this, R.anim.left_out);
                    viewFlipper.showPrevious();
                }
                break;
            }

        }

        return super.onTouchEvent(event);
    }

    private View getImageView(int i) {
        //这种方式的上下文是什么或者控件具体是什么
        ImageView imageView = new ImageView(this);
        //这种方式imageview大小是取决于图片本身宽高
//        imageView.setImageResource(i);
        imageView.setBackgroundResource(i);
        return imageView;
    }


}
