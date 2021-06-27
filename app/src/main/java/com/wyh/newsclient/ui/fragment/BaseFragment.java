package com.wyh.newsclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wyh.newsclient.ui.activity.MainActivity;

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initViews(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
        initListeners();
    }
    public abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    public abstract void initDatas();
    public abstract void initListeners();
    public abstract void processClick(View view);

    @Override
    public void onClick(View v) {
        processClick(v);
    }
    /*
    控制侧边栏的显示与否
     */
    public void setSlidingMenuEnable(boolean isEnable) {
        SlidingMenu slidingMenu = ((MainActivity) getActivity()).getSlidingMenu();
        if (isEnable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
