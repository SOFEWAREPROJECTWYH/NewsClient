package com.wyh.newsclient.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public abstract class BaseSlidingMenuActivity extends SlidingFragmentActivity implements View.OnClickListener{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initDatas();
        initListeners();
    }
    public abstract void initViews();
    public abstract void initDatas();
    public abstract void initListeners();
    public abstract void processClick(View view);
    @Override
    public void onClick(View v) {
        processClick(v);
    }


}
