package wdy.life.library;

import android.content.Intent;
import android.os.Bundle;

/**
 * 作者：王东一
 * 创建时间：2017/11/21.
 * 生命周期监听
 */

public interface LifeListener {
    public void onCreate(Bundle bundle);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onActivityResult(int requestCode, int resultCode, Intent data);
}
