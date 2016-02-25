package com.example.gypsophila.asynctaskload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gypsophila.dailytest.R;

import java.util.List;

/**
 * Created by Gypsophila on 2016/2/25.
 */
public class NewsAdapter extends BaseAdapter {

    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

    public NewsAdapter(List<NewsBean> list, Context context) {
        mList = list;
        mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 利用listview缓存机制，查看是否缓存中有可服用的view,没有才创建。
         * viewholder是用于保存可服用的布局中每个view，避免多次的findviewbyid操作
         */
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.asyncload_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.asyncload_ivIcon);
            viewHolder.title = (TextView) convertView.findViewById(R.id.asyncload_tvTitle);
            viewHolder.content = (TextView) convertView.findViewById(R.id.asyncload_tvContent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //先使用默认
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        //做imageview与要显示的图片的绑定
        viewHolder.imageView.setTag(mList.get(position).imgUrl);
//        new ImageLoader().showImageByThread(mList.get(position).imgUrl, viewHolder.imageView);
//        new ImageLoader().showImageByAsyncTask(viewHolder.imageView, mList.get(position).imgUrl);
        mImageLoader.showImageByAsyncTask(viewHolder.imageView, mList.get(position).imgUrl);
        viewHolder.title.setText(mList.get(position).title);
        viewHolder.content.setText(mList.get(position).content);
        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView title,content;
    }
}
