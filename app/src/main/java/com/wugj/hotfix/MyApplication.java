package com.wugj.hotfix;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/7/24
 * </br>
 * version:
 */
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        FixDexUtil.loadFixDex(this);
    }
}
