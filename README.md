# LifeObserved
监听activity生命周期
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
