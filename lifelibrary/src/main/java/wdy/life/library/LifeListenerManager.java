package wdy.life.library;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王东一
 * 创建时间：2017/6/28.
 */

public class LifeListenerManager {
    private List<LifeListener> listeners = new ArrayList<LifeListener>();

    void addLifeListener(LifeListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeLifeListener(LifeListener listener) {
        listeners.remove(listener);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        for (LifeListener listener : listeners) {
            if (listener != null) {
                ((LifeListener) listener).onCreate(savedInstanceState);
            }
        }
    }


    private void onStart() {
        for (LifeListener listener : listeners) {
            if (listener != null) {
                ((LifeListener) listener).onStart();
            }
        }
    }


    private void onResume() {
        for (LifeListener listener : listeners) {
            if (listener != null) {
                ((LifeListener) listener).onResume();
            }
        }
    }


    private void onPause() {
        for (LifeListener listener : listeners) {
            if (listener != null) {
                ((LifeListener) listener).onPause();
            }
        }
    }


    private void onStop() {
        for (LifeListener listener : listeners) {
            if (listener != null) {
                ((LifeListener) listener).onStop();
            }
        }
    }


    private void onDestroy() {
        //倒过来循环防止删除时有影响
        for (int i = listeners.size() - 1; i >= 0; i--) {
            if (listeners.get(i) != null) {
                LifeListener listener = (LifeListener) listeners.get(i);
                listener.onDestroy();
                recycleListener(listener);//回收防止持有页面对象
            }
        }
    }

    /**
     * 权限申请的回调
     *
     * @ requestCode
     * @ permissions
     * @ grantResults
     */
    private void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for (LifeListener listener : listeners) {
            if (listener instanceof PermissionListener) {
                ((PermissionListener) listener).onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    private void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (LifeListener listener : listeners) {
            if (listener != null) {
                listener.onActivityResult(requestCode, resultCode, data);
            }

        }
    }

    /**
     * 直接全部回收，页面回收之后已经没有必要继续监听
     * <p>
     * <p>
     * 回收本身就是Activity或Fragment的listener
     * 否则页面会被持有导致无法回收
     *
     * @ listener
     */
    private void recycleListener(LifeListener listener) {
        listeners.remove(listener);
        listener = null;
    }
}
