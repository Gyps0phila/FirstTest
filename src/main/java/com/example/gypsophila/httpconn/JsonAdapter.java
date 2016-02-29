package com.example.gypsophila.httpconn;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gypsophila.dailytest.R;

import java.util.List;

/**
 * Created by Gypsophila on 2016/2/29.
 */
public class JsonAdapter extends BaseAdapter {

    private List<Person> mList;
    private LayoutInflater mInflater;
    private Context mContext;
    private Handler mHandler = new Handler();

    public JsonAdapter(List<Person> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public JsonAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Person> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.json_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = mList.get(position);

        viewHolder.name.setText(person.getName());
        //有可能出错android.content.res.Resources$NotFoundException: String resource ID #0xc
        //age为int，系统去寻找资源
        viewHolder.age.setText(""+person.getAge());
        viewHolder.school1.setText(person.getSchList().get(0).getSchoolName());
        viewHolder.school2.setText(person.getSchList().get(1).getSchoolName());
        //获取图片再次进行网络请求，开个线程

        new ImageThread(person.getUrl(),viewHolder.imageView,mHandler).start();

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView name,age,school1,school2;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.json_img);
            name = (TextView) view.findViewById(R.id.json_name);
            age = (TextView) view.findViewById(R.id.json_age);
            school1 = (TextView) view.findViewById(R.id.json_school1);
            school2 = (TextView) view.findViewById(R.id.json_school2);
        }
    }
}
