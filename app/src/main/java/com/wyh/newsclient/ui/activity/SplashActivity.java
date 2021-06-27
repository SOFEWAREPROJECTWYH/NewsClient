package com.wyh.newsclient.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.wyh.newsclient.R;

public class SplashActivity extends BaseActivity {
    private LinearLayout ll_splash;
    @Override
    public void initViews() {
        setContentView(R.layout.splash_main);
        ll_splash=findViewById(R.id.ll_splash);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(ll_splash,"rotation",0,360*3).setDuration(2000),ObjectAnimator.ofFloat(ll_splash,"scaleX" ,0,1).setDuration(2000),
                ObjectAnimator.ofFloat(ll_splash,"scaleY",0,1),
                ObjectAnimator.ofFloat(ll_splash,"alpha",0,1).setDuration(3000));
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent=new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
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
}
