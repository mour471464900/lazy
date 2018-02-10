package com.haohao.lazy.english.lazyenglish.ui.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.haohao.lazy.english.lazyenglish.R
import com.haohao.lazy.english.lazyenglish.utils.LogUtil

/**
 * Created by Administrator on 2018/2/10.
 */
class NotificationService : Service() {

    companion object {
        const val TAG = "NotificationService"
        const val CHANNEL_ID = "1001"
        const val IS_NOTIFICATION = "isNotification"
    }


    private lateinit var notification: Notification

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtil.e(TAG, "onStartCommand")
        val booleanExtra = intent?.getBooleanExtra(IS_NOTIFICATION, false)
        when (booleanExtra) {
            true -> createNotification()
            false -> stopForeground(true)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        LogUtil.e(TAG, "onCreate")
    }

    private fun createNotification() {
        notification = NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher).setAutoCancel(false).setOngoing(false).setShowWhen(true)
                .setContentTitle("骑士大胜老鹰25分")
                .setContentText("詹皇22+12+17科沃尔7三分 骑士残阵大胜老鹰")
                .build()
        startForeground(1002, notification)
    }
}