package com.wyh.newsclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.viewpagerindicator.TabPageIndicator;
import com.wyh.newsclient.R;
import com.wyh.newsclient.model.NewsData;

import java.util.ArrayList;
import java.util.List;

import adapter.NewsTabFragment;
import adapter.NewsTabPagerAdapter;

public class NewsDetailFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    TextView tv_title;
    private List<NewsData.NewsTabData> newsTabDatas;
    private ViewPager vp_news_tab;
    private TabPageIndicator tab_indicator;
    private NewsTabPagerAdapter adapter;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.from(getActivity()).inflate(R.layout.news_detail_fragment,null);
        tv_title=rootView.findViewById(R.id.tv_title);
        vp_news_tab = rootView.findViewById(R.id.vp_news_tab);
        tab_indicator = rootView.findViewById(R.id.tab_indicator);
        return rootView;
    }

    @Override
    public void initDatas() {
        tv_title.setText("新闻");
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void processClick(View view) {

    }
    public void setNewsTabDatas(List<NewsData.NewsTabData> newsTabDatas) {
        this.newsTabDatas = newsTabDatas;

        List<NewsTabFragment> newsTabFragments = new ArrayList<>();
        for (int i = 0; i < newsTabDatas.size(); ++i) {
            NewsTabFragment newsTabFragment = new NewsTabFragment();
            newsTabFragment.setNewsTabData(newsTabDatas.get(i));
            newsTabFragments.add(newsTabFragment);
        }
        adapter = new NewsTabPagerAdapter(getActivity().getSupportFragmentManager(), newsTabFragments);


        vp_news_tab.setAdapter(adapter);
                 /*
        需要viewpager有adapter之后才能setViewPager
         */
        tab_indicator.setViewPager(vp_news_tab);
        tab_indicator.setOnPageChangeListener(this);
        tab_indicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position != 0) {
            setSlidingMenuEnable(false);
        }else{
            setSlidingMenuEnable(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
