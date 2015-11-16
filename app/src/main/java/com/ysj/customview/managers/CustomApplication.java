package com.ysj.customview.managers;

import android.app.Application;

import com.ysj.log.L;

/**
 * Created by yushaojian on 2015 11 15.
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        L.hideThreadInfo().methodCount(0);
    }
}
