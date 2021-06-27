package adapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;
import com.wyh.newsclient.Constants;
import com.wyh.newsclient.R;
import com.wyh.newsclient.model.NewsData;
import com.wyh.newsclient.model.TabNewsData;
import com.wyh.newsclient.ui.fragment.BaseFragment;
import com.wyh.newsclient.view.RefreshListView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewsTabFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private NewsData.NewsTabData newsTabData;
    private ViewPager vp_news_tab;
    private RefreshListView lv_news;
    private adapter.TabDetailListAdapter adapter;
    private adapter.TabDetailTopNewsPagerAdapter topNewsPagerAdapter;
    private ViewPager vp_top_news;
    private TextView tv_topnews_title;
    private CirclePageIndicator circlePageIndicator;
    private TabNewsData tabNewsData;
    private String moreUrl = "";


    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.from(getActivity()).inflate(R.layout.news_tab_fragment, null);
        View headerView = inflater.from(getActivity()).inflate(R.layout.top_news_header, null);

        lv_news =  rootView.findViewById(R.id.lv_news);
        vp_top_news =  headerView.findViewById(R.id.vp_top_news);

        tv_topnews_title = headerView.findViewById(R.id.tv_topnews_title);
        circlePageIndicator =  (CirclePageIndicator) headerView.findViewById(R.id.circle_indicator);

        lv_news.addHeaderView(headerView);


        return rootView;
    }

    @Override
    public void initDatas() {
        getDataFromServer(Constants.SERVER_URL + newsTabData.url);

    }

    @Override
    public void initListeners() {
        lv_news.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer(Constants.SERVER_URL + newsTabData.url);
            }

            @Override
            public void loadMore() {
                if (TextUtils.isEmpty(moreUrl)) {
                    lv_news.onRefreshComplete(false);
                    return;
                }else{
                    getMoreDataFromServer();
                }
            }
        });
    }

    @Override
    public void processClick(View view) {

    }

    public void setNewsTabData(NewsData.NewsTabData newsTabData) {
        this.newsTabData = newsTabData;
    }

    public NewsData.NewsTabData getNewsTabData() {
        return newsTabData;
    }

    public void getDataFromServer(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        System.out.println("--------->tab的请求的结果是:" + result);
                        processResult(result);
                    } else {
                        System.out.println("--------->请求失败");
                        lv_news.onRefreshComplete(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void processResult(String result) {
        tabNewsData = new Gson().fromJson(result, TabNewsData.class);
        adapter = new adapter.TabDetailListAdapter(tabNewsData.data.news);
        topNewsPagerAdapter = new adapter.TabDetailTopNewsPagerAdapter(tabNewsData.data.topnews);

        moreUrl = tabNewsData.data.more;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lv_news.setAdapter(adapter);
                vp_top_news.setAdapter(topNewsPagerAdapter);

                circlePageIndicator.setViewPager(vp_top_news);

                tv_topnews_title.setText(tabNewsData.data.topnews.get(0).title);

                lv_news.onRefreshComplete(true);
            }
        });

        circlePageIndicator.setOnPageChangeListener(this);
        System.out.println("-------->tabNewsData: " + tabNewsData);
    }

    public void processMoreResult(String result) {
        tabNewsData = new Gson().fromJson(result, TabNewsData.class);
        adapter.appendNews(tabNewsData.data.news);
        moreUrl = tabNewsData.data.more;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetInvalidated();

                lv_news.onRefreshComplete(true);
            }
        });

        circlePageIndicator.setOnPageChangeListener(this);
        System.out.println("-------->tabNewsData: " + tabNewsData);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_topnews_title.setText(tabNewsData.data.topnews.get(position).title);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void getMoreDataFromServer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(Constants.SERVER_URL + tabNewsData.data.more).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String result = response.body().string();

                        System.out.println("--------->tab的请求的结果是:" + result);

                        processMoreResult(result);


                    } else {
                        System.out.println("--------->请求失败");
                        lv_news.onRefreshComplete(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
