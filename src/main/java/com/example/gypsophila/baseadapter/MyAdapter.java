package com.example.gypsophila.baseadapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gypsophila.dailytest.R;

import java.util.List;

/**
 * Created by Gypsophila on 2016/2/23.
 */
public class MyAdapter extends BaseAdapter {
    private List<ItemBean> mlist;
    private LayoutInflater mInflater;
    //通过构造方法将数据源与数据适配器关联
    public MyAdapter(List<ItemBean> list,Context context) {
        mlist = list;
        mInflater = LayoutInflater.from(context);
    }
    //返回listview需要显示的数据数量
    @Override
    public int getCount() {
        return mlist.size();
    }

    //指定索引对应的数据项
    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }
    //对应的索引
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 这种方式没有利用listview的缓存机制，每次创建新的view效率低下
         * 295702876
         */
//        View view = mInflater.inflate(R.layout.item, null);
//        ImageView img = (ImageView) view.findViewById(R.id.item_img);
//        TextView title = (TextView) view.findViewById(R.id.item_title);
//        TextView content = (TextView) view.findViewById(R.id.item_content);
//        ItemBean itemBean = mlist.get(position);
//        img.setImageResource(itemBean.ItemImgResId);
//        title.setText(itemBean.ItemTitle);
//        content.setText(itemBean.ItemContent);
//        return view;

        /**
         * 这种方式虽然仅仅是converview处理不同，但是避免了重复创建view的操作，布局转化为view时候耗时的
         * 利用listview缓存特性，没有缓存才创建新的view
         * ！！！但另外的findViewById依然浪费大量时间 244784106
         */
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.item, null);
//        }
//        ImageView img = (ImageView) convertView.findViewById(R.id.item_img);
//        TextView title = (TextView) convertView.findViewById(R.id.item_title);
//        TextView content = (TextView) convertView.findViewById(R.id.item_content);
//        ItemBean itemBean = mlist.get(position);
//        img.setImageResource(itemBean.ItemImgResId);
//        title.setText(itemBean.ItemTitle);
//        content.setText(itemBean.ItemContent);
//        return convertView;


        /**
         * 1.当converView为空，在布局转化为view同时，只实例化一次
         * 将控件保存在viewholder中，避免多次findviewbyid实例化
         * 2.通过settag将viewholder与convertview绑定
         * 232659581
         */
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean itemBean = mlist.get(position);
        viewHolder.imageView.setImageResource(itemBean.ItemImgResId);
        viewHolder.title.setText(itemBean.ItemTitle);
        viewHolder.content.setText(itemBean.ItemContent);
        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
