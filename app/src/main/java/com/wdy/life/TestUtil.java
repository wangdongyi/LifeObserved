package com.wdy.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import wdy.life.library.LifeListener;
import wdy.life.library.LifeManager;

/**
 * 作者：王东一
 * 创建时间：2017/11/21.
 */

public class TestUtil {
    public static final String Tag="TestUtil";
    public TestUtil(Activity activity){
        LifeManager.getInstance().ObserveActivity(activity, new LifeListener() {
            @Override
            public void onCreate(Bundle bundle) {
                Log.d(Tag,"onCreate");
            }

            @Override
            public void onStart() {
                Log.d(Tag,"onStart");
            }

            @Override
            public void onResume() {
                Log.d(Tag,"onResume");
            }

            @Override
            public void onPause() {
                Log.d(Tag,"onPause");
            }

            @Override
            public void onStop() {
                Log.d(Tag,"onStop");
            }

            @Override
            public void onDestroy() {
                Log.d(Tag,"onDestroy");
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                Log.d(Tag,"onActivityResult");
            }
        });
    }
}
