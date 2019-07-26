package com.wugj.hotfix;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/7/24
 * </br>
 * version:
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,};
            for (String perm : perms){
                if (checkCallingOrSelfPermission(perm) == PackageManager.PERMISSION_DENIED){
                    requestPermissions(perms,200);
                }
            }
        }

    }

    public void jump(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



}
