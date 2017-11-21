package wdy.life.library;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

/**
 * 作者：王东一
 * 创建时间：2017/11/21.
 */

public class SupportActLifeListenerFragment extends Fragment {
    private LifeListenerManager listenerManager;

    public void setActLifeListenerManager(LifeListenerManager manager) {
        listenerManager = manager;
    }

    public LifeListenerManager getLifeListenerManager() {
        return listenerManager;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void LifeRequestPermissions(final String[] array, final int code) {
        if (!isAdded()) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isAdded()) {
                        SupportActLifeListenerFragment.this.requestPermissions(array, code);
                    }
                }
            }, 500);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isAdded()) {
                SupportActLifeListenerFragment.this.requestPermissions(array, code);
            }
        }
    }

    public void LifeStartActivityResult(final Intent intent, final int code) {
        if (!isAdded()) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SupportActLifeListenerFragment.this.startActivityForResult(intent, code);
                }
            }, 500);
        } else {
            if (isAdded()) {
                SupportActLifeListenerFragment.this.startActivityForResult(intent, code);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onDestroy();
        setActLifeListenerManager(null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getLifeListenerManager() != null)
            getLifeListenerManager().onActivityResult(requestCode, resultCode, data);
    }
}
