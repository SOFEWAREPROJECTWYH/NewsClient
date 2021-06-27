package com.wyh.newsclient.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;


public abstract class BaseActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
