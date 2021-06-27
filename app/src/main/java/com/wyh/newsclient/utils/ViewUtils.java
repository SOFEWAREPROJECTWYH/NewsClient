package com.wyh.newsclient.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ViewUtils {
    public static int dp2px(Context context, int dp){
        if(context==null){
            return dp;
        }
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        float density=metrics.density;
        return (int)(dp*density);
    }
}
