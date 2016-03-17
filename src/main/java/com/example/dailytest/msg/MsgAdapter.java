package com.example.dailytest.msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weather.dailytest.R;

import java.util.List;

/**
 * Created by Gypsophila on 2016/3/5.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {

    private int resLayout;
    private LayoutInflater mInflater;
    public MsgAdapter(Context context, int resource, List<Msg> objects) {
        super(context, resource, objects);
        resLayout = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得一个msg实例
        Msg msg = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(resLayout, null);
            viewHolder = new ViewHolder();
            viewHolder.left_layout = (LinearLayout) convertView.findViewById(R.id.left_layout);
            viewHolder.right_layout = (LinearLayout) convertView.findViewById(R.id.right_layout);
            viewHolder.left_tv = (TextView) convertView.findViewById(R.id.left_msg);
            viewHolder.right_tv = (TextView) convertView.findViewById(R.id.right_msg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //接受消息，就把右边的线性布局隐藏
            viewHolder.left_layout.setVisibility(View.VISIBLE);
            viewHolder.right_layout.setVisibility(View.GONE);
            viewHolder.left_tv.setText(msg.getContent());
        } else if (msg.getType()==Msg.TYPE_SENT) {
            viewHolder.right_layout.setVisibility(View.VISIBLE);
            viewHolder.left_layout.setVisibility(View.GONE);
            viewHolder.right_tv.setText(msg.getContent());
        }

        return convertView;
    }

    class ViewHolder {
        LinearLayout right_layout,left_layout;
        TextView right_tv,left_tv;
    }
}
