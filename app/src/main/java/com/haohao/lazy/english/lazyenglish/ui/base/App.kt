package com.haohao.lazy.english.lazyenglish.ui.base

import android.app.Application
import com.haohao.lazy.english.lazyenglish.R
import com.iflytek.cloud.SpeechUtility

/**
 * Created by Administrator on 2018/2/7.
 */
class App: Application() {
    override fun onCreate() {
        //       初始化appid
        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id))
        super.onCreate()
    }
}