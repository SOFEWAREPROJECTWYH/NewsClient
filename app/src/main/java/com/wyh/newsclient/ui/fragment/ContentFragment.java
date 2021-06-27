package com.wyh.newsclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wyh.newsclient.R;
import com.wyh.newsclient.model.NewsData;
import com.wyh.newsclient.ui.activity.MainActivity;
import com.wyh.newsclient.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.ContentPageAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class ContentFragment extends BaseFragment{

    private ViewPager vp_content;
    private RadioGroup rg_bar;
    private NewsDetailFragment newsDetailFragment;
    private NewsData newsData;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview= inflater.from(getActivity()).inflate(R.layout.content_fragment_main,null);
        vp_content = rootview.findViewById(R.id.vp_content);
        rg_bar = rootview.findViewById(R.id.rg_bar);
        return rootview;
    }

    @Override
    public void initDatas() {
//        List<BaseFragment> fragments=new ArrayList<>();
//        fragments.add(new HomeDetailFragment());
//        fragments.add(new NewsDetailFragment());
//        fragments.add(new SmartDetailFragment());
//        fragments.add(new GovDetailFragment());
//        fragments.add(new SettingDetailFragment());
//        ContentPageAdapter adapter=new ContentPageAdapter(getActivity().getSupportFragmentManager(),fragments);
//        vp_content.setAdapter(adapter);
        getDataFromServer();
    }

    @Override
    public void initListeners() {
        rg_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_home){
                    vp_content.setCurrentItem(0,false);
                    setSlidingMenuEnable(false);
                }else if(checkedId==R.id.rb_news){
                    vp_content.setCurrentItem(1,false);
                    setSlidingMenuEnable(true);
                }else if(checkedId==R.id.rb_smart){
                    vp_content.setCurrentItem(2,false);
                    setSlidingMenuEnable(false);
                }else if(checkedId==R.id.rb_gov){
                    vp_content.setCurrentItem(3,false);
                    setSlidingMenuEnable(false);
                }else if(checkedId==R.id.rb_settings){
                    vp_content.setCurrentItem(4,false);
                    setSlidingMenuEnable(false);
                }
            }
        });
    }

    @Override
    public void processClick(View view) {

    }
    public void setSlidingMenuEnable(boolean isEnable){
        SlidingMenu slidingMenu=((MainActivity)getActivity()).getSlidingMenu();
        if(isEnable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else{
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
    public void getDataFromServer() {
        new Thread(() -> {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(Constants.CATEGORIES_URL).build();
            try {
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: ");
                        Log.d(TAG,e+"");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: Success!");
                        if (response.isSuccessful()) {
                            String result = response.body().string();
                            newsData = new Gson().fromJson(result, NewsData.class);
                            List<String> titles = new ArrayList();
                            for (int i = 0; i < newsData.data.size(); ++i) {
                                titles.add(newsData.data.get(i).title);
                            }
                            ((MainActivity) getActivity()).getLeftMenuFragment().addTitles(titles);
                            getActivity().runOnUiThread(() -> {
                                List<BaseFragment> fragments = new ArrayList<>();
                                fragments.add(new HomeDetailFragment());
                                newsDetailFragment = new NewsDetailFragment();
                                fragments.add(newsDetailFragment);
                                fragments.add(new SmartDetailFragment());
                                fragments.add(new GovDetailFragment());
                                fragments.add(new SettingDetailFragment());
                                ContentPageAdapter adapter = new ContentPageAdapter(getActivity().getSupportFragmentManager(), fragments);
                                vp_content.setAdapter(adapter);
                                newsDetailFragment.setNewsTabDatas(newsData.data.get(0).children);
                            });
                            System.out.println("-------->newsData: " + newsData);
                        } else {
                            System.out.println("------->请求失败");
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
