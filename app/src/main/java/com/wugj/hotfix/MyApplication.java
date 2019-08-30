package com.wugj.hotfix;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.wugj.hotfix.dex.FindClass;
import com.wugj.hotfix.fix.FixDexUtil;

import java.util.List;

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
//        MultiDex.install(this);
        FixDexUtil.loadFixDex(this);

        //只能运行时获取修改文件
        //FindClass.getChangeClass(this);

        //loadApps();
    }


    /**
     * 获取本机已安装apk的报名和LauncherActivity
     */
    private void loadApps() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = getPackageManager().queryIntentActivities(intent, 0);
        //for循环遍历ResolveInfo对象获取包名和类名
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packageName = info.activityInfo.packageName;
            CharSequence cls = info.activityInfo.name;
            CharSequence name = info.activityInfo.loadLabel(getPackageManager());
            Log.e("！！！！！", name + "----" + packageName + "----" + cls);
        }
    }
}
