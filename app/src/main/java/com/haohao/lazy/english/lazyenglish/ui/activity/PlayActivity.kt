package com.haohao.lazy.english.lazyenglish.ui.activity

import android.annotation.SuppressLint
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast
import com.haohao.lazy.english.lazyenglish.R
import com.haohao.lazy.english.lazyenglish.ui.service.MusicService
import com.haohao.lazy.english.lazyenglish.utils.LogUtil
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_play.*


/**
 *  播放列表
 */
class PlayActivity : BaseActivity() {

    private var mService: MusicService? = null

    companion object {
        const val STATE_PLAYING = 0  // 播放中
        const val STATE_PAUSE = 1    //  暂停
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_play
    }

    @SuppressLint("CheckResult")
    override fun initView() {
        super.initView()
        iv_play.tag = STATE_PAUSE
        RxView.clicks(iv_play).subscribe {
            val tag = iv_play.tag as Int
            when (tag) {
                STATE_PLAYING -> {
                    if(mService!=null)mService?.onPause()
                    iv_play.tag = STATE_PAUSE
                    iv_play.isSelected = false
                }
                STATE_PAUSE -> {
                    if(mService!=null)mService?.onPlay()
                    iv_play.tag = STATE_PLAYING
                    iv_play.isSelected = true
                }
            }
        }
        RxView.clicks(iv_mode).subscribe {
            if(mService!=null) Toast.makeText(this,"当前请求服务的次数:"+mService?.count,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, MusicService::class.java)
        bindService(intent,connection, Service.BIND_AUTO_CREATE)
    }

    // 服务连接器
    private var connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
//            表示服务断开连接
            LogUtil.e(TAG, "断开连接name:" + name.toString())
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtil.e(TAG,"开启连接服务")
            val binder = service as MusicService.MusicBinder
            mService = binder.service
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mService!=null) mService?.unbindService(connection)
    }
}