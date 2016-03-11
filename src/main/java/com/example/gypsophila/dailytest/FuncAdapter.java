package com.example.gypsophila.dailytest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Gypsophila on 2016/3/9.
 */
public class FuncAdapter extends BaseAdapter {

    private List<FuncBean> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public FuncAdapter(Context context, List<FuncBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.func_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.func_img);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.func_des);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(mList.get(position).getResId());
        viewHolder.textView.setText(mList.get(position).getFuncDescription());
        //！！由于在textview这边设置了点击事件监听，导致点击onItemClickListener点中文本时无效！

//        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(mContext,"you click"+mList.get(position).getFuncDescription(),Toast.LENGTH_SHORT).show();
////                String name = "com.example.gypsophila.dailytest.MainActivity";
//                String name = mList.get(position).getFuncDescription();
////                try {
////                    mContext.startActivity(new Intent(mContext, Class.forName(name)));//可行
////                } catch (ClassNotFoundException e) {
////                    e.printStackTrace();
////                    Log.i("nofound", name);
////                }
//            }
//        });

        return convertView;
    }

    class ViewHolder {

        public ImageView imageView;
        public TextView textView;

    }
}
