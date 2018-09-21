package wdy.life.library;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;

/**
 * 作者：王东一
 * 创建时间：2017/6/28.
 */

public class LifeManager {
    public static final String FRAGMENT_TAG = "ActivityLifeListenerFragment";

    private static volatile LifeManager mInstance;

    private LifeManager() {

    }

    private FragmentManager fragmentManager;

    public static LifeManager getInstance() {
        if (null == mInstance) {
            synchronized (LifeManager.class) {
                if (null == mInstance) {
                    mInstance = new LifeManager();
                }
            }
        }
        return mInstance;
    }


    /**
     * 开始监听生命周期
     */
    public void ObserveActivity(Activity activity, LifeListener Listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a observe for a destroyed activity");
        }
        if (!(Looper.myLooper() == Looper.getMainLooper())) {
            throw new IllegalArgumentException("You must start a observe on mainThread");
        }
        fragmentManager = activity.getFragmentManager();
        SupportActLifeListenerFragment supportFragment = findFragment(fragmentManager);//找到绑定的Fragment
        LifeListenerManager manager = findLifeListenerManager(supportFragment);//找到指定Fragment的ActLifeListenerManager
        manager.addLifeListener(Listener);//添加监听
    }

    public void requestPermissions(String[] permissions, int requestCode) {
        SupportActLifeListenerFragment supportFragment = findFragment(fragmentManager);//找到绑定的Fragment
        supportFragment.LifeRequestPermissions(permissions, requestCode);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        SupportActLifeListenerFragment supportFragment = findFragment(fragmentManager);//找到绑定的Fragment
        supportFragment.LifeStartActivityResult(intent, requestCode);
    }

    /**
     * 开始监听生命周期
     */
    public void ObserveActivity(android.support.v4.app.Fragment fragment, LifeListener Listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && fragment.getActivity().isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a observe for a destroyed activity");
        }
        if (!(Looper.myLooper() == Looper.getMainLooper())) {
            throw new IllegalArgumentException("You must start a observe on mainThread");
        }
        fragmentManager = fragment.getActivity().getFragmentManager();
        SupportActLifeListenerFragment supportFragment = findFragment(fragmentManager);//找到绑定的Fragment
        LifeListenerManager manager = findLifeListenerManager(supportFragment);//找到指定Fragment的ActLifeListenerManager
        manager.addLifeListener(Listener);//添加监听
    }

    /**
     * 开始监听生命周期
     */
    public void ObserveActivity(Fragment fragment, LifeListener Listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && fragment.getActivity().isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a observe for a destroyed activity");
        }
        if (!(Looper.myLooper() == Looper.getMainLooper())) {
            throw new IllegalArgumentException("You must start a observe on mainThread");
        }
        fragmentManager = fragment.getActivity().getFragmentManager();
        SupportActLifeListenerFragment supportFragment = findFragment(fragmentManager);//找到绑定的Fragment
        LifeListenerManager manager = findLifeListenerManager(supportFragment);//找到指定Fragment的ActLifeListenerManager
        manager.addLifeListener(Listener);//添加监听
    }

    /**
     * 找到用于监听生命周期的空白的Fragment
     */
    public SupportActLifeListenerFragment findFragment(FragmentManager fm) {
        SupportActLifeListenerFragment current = (SupportActLifeListenerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {//没有找到，则新建
            current = new SupportActLifeListenerFragment();
            fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();//添加Fragment
        }
        return current;
    }

    /**
     * 用于获取管理对应Fragment的ActLifeListenerManager
     *
     * @param fragment
     * @return
     */
    private LifeListenerManager findLifeListenerManager(SupportActLifeListenerFragment fragment) {
        LifeListenerManager manager = fragment.getLifeListenerManager();
        if (null == manager) {
            manager = new LifeListenerManager();
        }
        fragment.setActLifeListenerManager(manager);
        return manager;
    }

}
