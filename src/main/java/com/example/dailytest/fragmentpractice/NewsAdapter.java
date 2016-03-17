package com.example.dailytest.fragmentpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.weather.dailytest.R;

import java.util.List;

/**
 * Created by Gypsophila on 2016/3/12.
 */
public class NewsAdapter extends ArrayAdapter<NewsBean> {
    private int resLayout;

    public NewsAdapter(Context context, int resource, List<NewsBean> objects) {
        super(context, resource, objects);
        resLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        NewsBean news = getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resLayout, null);
        } else {
            view = convertView;
        }
        TextView newsTitle = (TextView) view.findViewById(R.id.news_title);
        newsTitle.setText(news.getNewsTitle());
        return view;
    }
}
