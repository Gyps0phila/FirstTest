package com.example.gypsophila.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.gypsophila.dailytest.BaseAty;
import com.example.gypsophila.dailytest.MainActivity;
import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/10.
 */
public class AnimationAty extends BaseAty {


    private Animation animation;
    private ImageView ani_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        ani_img = (ImageView) findViewById(R.id.ani_img);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alpha: {
                animation = AnimationUtils.loadAnimation(AnimationAty.this,R.anim.alpha);
                ani_img.startAnimation(animation);

                break;
            }
            case R.id.btn_scale: {
                animation = AnimationUtils.loadAnimation(AnimationAty.this, R.anim.scale);
                ani_img.startAnimation(animation);
                break;
            }
            case R.id.btn_translate: {
                animation = AnimationUtils.loadAnimation(AnimationAty.this, R.anim.translate);
                ani_img.startAnimation(animation);

                break;
            }
            case R.id.btn_rotate: {
                animation = AnimationUtils.loadAnimation(AnimationAty.this, R.anim.rotate);
                ani_img.startAnimation(animation);
                break;
            }
            case R.id.continue1: {
                animation = AnimationUtils.loadAnimation(AnimationAty.this, R.anim.translate);
                ani_img.startAnimation(animation);
                final Animation animation1 = AnimationUtils.loadAnimation(AnimationAty.this, R.anim.rotate);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ani_img.startAnimation(animation1);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                break;
            }

            case R.id.continue2: {
                animation = AnimationUtils.loadAnimation(AnimationAty.this, R.anim.continue2);
                ani_img.startAnimation(animation);
                break;
            }
            case R.id.btn_flash: {
                animation = new AlphaAnimation(0.1f, 1.0f);
                animation.setDuration(100);
                //设置倒序重复
                animation.setRepeatMode(Animation.REVERSE);
                animation.setRepeatCount(10);
                ani_img.startAnimation(animation);
                break;
            }

            case R.id.btn_in_out: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zone_in,R.anim.zone_out);
                break;
            }

            case R.id.btn_layout: {
                Intent intent = new Intent(this, AniListViewAty.class);
                startActivity(intent);
                break;
            }
            case R.id.frame: {
                ani_img.setImageResource(R.drawable.ani_list);
                AnimationDrawable ad = (AnimationDrawable) ani_img.getDrawable();
                ad.start();
            }
        }
    }
}
