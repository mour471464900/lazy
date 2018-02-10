package com.haohao.lazy.english.lazyenglish.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Administrator on 2018/2/8.
 */
object AppUtil {

    var TAG ="AppUtil"

    /**
     * 网络是否可用
     *
     */
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
        } else {
            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        LogUtil.e(TAG,"有可用网络")
                        return true
                    }
                }
            }
        }
        return false
    }
}