package com.example.weather.fragmentpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.weather.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/3/12.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener{

    private NewsAdapter adapter;
    private List<NewsBean> mList;
    private ListView newsTitleListView;
    private boolean isTwoPage;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mList = getNews();
        adapter = new NewsAdapter(activity, R.layout.news_item, mList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);

        return view;
    }

    //是确认activity一定已经创建完成时候被调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断单双页
        if (getActivity().findViewById(R.id.news_content_layout) == null) {
            isTwoPage = false;
        } else {
            isTwoPage = true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //单页则进行跳转页面，双页则加载出右边的内容
        NewsBean news = mList.get(position);
        if (isTwoPage) {
            //
            NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            fragment.refresh(news.getNewsTitle(), news.getNewsContent());
        } else {
            NewsContentAty.startAction(getActivity(), news.getNewsTitle(), news.getNewsContent());
        }
    }

    private List<NewsBean> getNews() {
        List<NewsBean> newsList = new ArrayList<NewsBean>();
        NewsBean news1 = new NewsBean();
        news1.setNewsTitle("Succeed in College as a Learning Disabled Student");
        news1.setNewsContent("College freshmen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar"
                + "dining hall food. Students with learning disabilities will face these transitions while also grappling with a few more hurdles.");
        newsList.add(news1);
        NewsBean news2 = new NewsBean();
        news2.setNewsTitle("Google Android exec poached by China's Xiaomi");
        news2.setNewsContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup"
                + "for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
        return newsList;
    }
}
