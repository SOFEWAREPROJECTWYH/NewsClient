package com.wyh.newsclient.utils;


import com.wyh.newsclient.Constants;


public class StringUtils {
    public static String fixUrl(String url) {
        return url.replace("http://47.108.169.225:29999/", Constants.SERVER_URL_WITHOUT);
    }
}
