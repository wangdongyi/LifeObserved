package com.wdy.life;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import wdy.life.library.LifeListener;
import wdy.life.library.LifeManager;
import wdy.life.library.PermissionListener;
import wdy.life.library.SupportActLifeListenerFragment;

/**
 * 作者：王东一
 * 创建时间：2017/11/21.
 */

public class TestUtil {
    public static final String Tag = "TestUtil";

    public TestUtil(final Activity activity) {
        LifeManager.getInstance().ObserveActivity(activity, new PermissionListener() {
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                Log.e(Tag, "onRequestPermissionsResult");
            }

            @Override
            public void onCreate(Bundle bundle) {
                Log.d(Tag, "onCreate");
            }

            @Override
            public void onStart() {
                Log.e(Tag, "onStart");
                List<String> lackedPermission = new ArrayList<String>();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!(activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
                        lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
                    }
                    if (!(activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                        lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }

                    if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                        lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
                    }

                    // 权限都已经有了，那么直接调用SDK
                    if (lackedPermission.size() == 0) {
                    } else {
                        // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
                        String[] requestPermissions = new String[lackedPermission.size()];
                        lackedPermission.toArray(requestPermissions);
                        SupportActLifeListenerFragment fragment = LifeManager.getInstance().findFragment(activity.getFragmentManager());
                        fragment.LifeRequestPermissions(requestPermissions,1024);
//                        FragmentCompat.requestPermissions(fragment, requestPermissions, 1024);
//                        ActivityCompat.requestPermissions(activity,requestPermissions,1024);
                    }
                }

            }

            @Override
            public void onResume() {
                Log.e(Tag, "onResume");
            }

            @Override
            public void onPause() {
                Log.e(Tag, "onPause");
            }

            @Override
            public void onStop() {
                Log.e(Tag, "onStop");
            }

            @Override
            public void onDestroy() {
                Log.e(Tag, "onDestroy");
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                Log.e(Tag, "onActivityResult");
            }
        });
    }
}
