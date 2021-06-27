package com.wyh.newsclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.wyh.newsclient.R;

public class GovDetailFragment extends BaseFragment{

    private TextView tv_title;

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.from(getActivity()).inflate(R.layout.gov_detail_fragment,null);
        tv_title=rootView.findViewById(R.id.tv_title);
        return rootView;
    }

    @Override
    public void initDatas() {
        tv_title.setText("政府事务");
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void processClick(View view) {

    }
}
