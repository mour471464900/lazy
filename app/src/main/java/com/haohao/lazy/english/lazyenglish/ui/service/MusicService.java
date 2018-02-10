package com.haohao.lazy.english.lazyenglish.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.haohao.lazy.english.lazyenglish.utils.LogUtil;

/**
 *
 */

public class MusicService extends Service {
    public static final String TAG = "MusicService";
    private MusicBinder  mBinder = new MusicBinder();
    private boolean isQuit;  // 是否停止服务
    private int count;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e(TAG, "invoke onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e(TAG, "创建服务:onCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isQuit){
                    try {
                        Thread.sleep(5*1000);
                        ++count;
                        LogUtil.e(TAG,"发送服务的次数："+count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }




    /**
     * 启动命令
     * 当另一个组件（如 Activity）通过调用 startService() 请求启动服务时，系统将调用此方法。一旦执行此方法，服务即会启动并可在后台无限期运行。
     * 如果自己实现此方法，则需要在服务工作完成后，通过调用 stopSelf() 或 stopService() 来停止服务。（在绑定状态下，无需实现此方法。）
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e(TAG, "启动服务onStartCommand invoke,当前启动指令:"+flags);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.e(TAG,"解除绑定：onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isQuit=true; // 停止服务
        onPause();
        LogUtil.e(TAG, "销毁服务:onDestroy invoke");
    }

    public int getCount() {
        return count;
    }

    public class  MusicBinder   extends Binder {
        public MusicService getService(){
            return  MusicService.this;
        }
    }

    public  void  onPlay(){
        LogUtil.e(TAG,"播放开始");
    }

    public void  onPause(){
        LogUtil.e(TAG,"播放暂停");
    }
}
