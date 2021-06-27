package com.wyh.newsclient.ui.activity;

import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wyh.newsclient.R;
import com.wyh.newsclient.ui.activity.BaseSlidingMenuActivity;
import com.wyh.newsclient.ui.fragment.ContentFragment;
import com.wyh.newsclient.ui.fragment.LeftMenuFragment;

public class MainActivity extends BaseSlidingMenuActivity {

    @Override
    public void initViews(){
        initFragments();
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu_main);
        SlidingMenu slidingMenu=getSlidingMenu();
        WindowManager windowManager=(WindowManager)getSystemService(WINDOW_SERVICE);
        int screenWidth=windowManager.getDefaultDisplay().getWidth();
        slidingMenu.setBehindOffset((int)(screenWidth*0.675f));
        slidingMenu.getTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void processClick(View view) {

    }
    public void initFragments(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,new ContentFragment(),"content_fragment");
        fragmentTransaction.replace(R.id.fl_left_menu,new LeftMenuFragment(), "left_menu_fragment");
        fragmentTransaction.commit();
    }
    public LeftMenuFragment getLeftMenuFragment() {
        return (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag("left_menu_fragment");
    }
}