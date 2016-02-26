package com.example.gypsophila.asynctaskload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gypsophila.dailytest.R;

import java.util.List;

/**
 * Created by Gypsophila on 2016/2/25.
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private int mStart,mEnd;
    public static String[] URLS;
    private boolean mFirstIn;

    public NewsAdapter(List<NewsBean> list, Context context,ListView listView) {
        mFirstIn = true;
        mList = list;
        mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(listView);
        URLS = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            URLS[i] = list.get(i).imgUrl;
        }
        listView.setOnScrollListener(this);
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

    /**
     * 这个第一次加载时不会被调用
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            //停止滑动，加载可见项
            mImageLoader.loadImages(mStart , mEnd);
        } else {
            //停止加载任务
            mImageLoader.cancelAllTasks();
        }
    }

    /**
     * 这个回调会被多次调用，初始化调用时候visibleItemCount=0,item还没有被加载，所以要跳过去>0才可
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //第一次预加载调用
        if (mFirstIn && visibleItemCount > 0) {
            mImageLoader.loadImages(mStart, mEnd);
            mFirstIn = false;
        }
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView title,content;
    }
}
