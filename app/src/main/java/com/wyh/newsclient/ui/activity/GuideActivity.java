package com.wyh.newsclient.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import adapter.GuidePageAdapter;
import com.wyh.newsclient.R;
import com.wyh.newsclient.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {
    int[] imgeRsIds=new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };
    List<ImageView> guideImgs;
    private ViewPager vp_guide;
    private LinearLayout ll_indicator_container;
    private int indicatorWidth;
    private ImageView iv_circle_indicator_selected;
    private Button bt_start;

    @Override
    public void initViews() {
        setContentView(R.layout.guide_main);
        vp_guide = findViewById(R.id.vp_guide);
        ll_indicator_container = findViewById(R.id.ll_indicator_container);
        iv_circle_indicator_selected = findViewById(R.id.iv_circle_indicator_selected);
        bt_start = findViewById(R.id.bt_start);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initDatas() {
        indicatorWidth= ViewUtils.dp2px(this,15);
        guideImgs=new ArrayList<>();
        for(int i=0;i<imgeRsIds.length;i++){
            ImageView imageView=new ImageView(this);
//            imageView.setImageResource(imgeRsIds[i]);
            imageView.setBackgroundResource(imgeRsIds[i]);
            guideImgs.add(imageView);
            View pointview=new View(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(indicatorWidth,indicatorWidth);
            if(i!=0){
                params.leftMargin=indicatorWidth;
            }
            pointview.setLayoutParams(params);
            pointview.setBackgroundResource(R.drawable.guide_circle_indicator_shape);
            ll_indicator_container.addView(pointview);
        }
        GuidePageAdapter adapter=new GuidePageAdapter(guideImgs);
        vp_guide.setAdapter(adapter);

    }

    @Override
    public void initListeners() {
        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float deltax=indicatorWidth*2*positionOffset+position*indicatorWidth*2;
                RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)iv_circle_indicator_selected.getLayoutParams();
                params.leftMargin=(int)deltax;
                iv_circle_indicator_selected.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if(position==guideImgs.size()-1){
                    bt_start.setVisibility(View.VISIBLE);
                }else{
                    bt_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void processClick(View view) {
        if (view.getId() == R.id.bt_start) {
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
