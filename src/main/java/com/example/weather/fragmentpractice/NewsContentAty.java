package com.example.weather.fragmentpractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.weather.dailytest.R;


/**
 * Created by Gypsophila on 2016/3/12.
 */
public class NewsContentAty extends Activity {

    public static void startAction(Context context,String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentAty.class);
        intent.putExtra("newsTitle", newsTitle);
        intent.putExtra("newsContent", newsContent);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        //接受跳转传入的值
        String newsTitle = getIntent().getStringExtra("newsTitle");
        String newsContent = getIntent().getStringExtra("newsContent");
        NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
        fragment.refresh(newsTitle, newsContent);
    }


}
