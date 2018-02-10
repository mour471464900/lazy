package com.haohao.lazy.english.lazyenglish.utils

import android.util.Log
import com.haohao.lazy.english.lazyenglish.BuildConfig

/**
 * Created by Administrator on 2018/2/7.
 */
object LogUtil {
    val isDebug = BuildConfig.DEBUG
    @JvmStatic
    fun e(Tag:String, content:String){
        if(isDebug) Log.e(Tag,content)
    }

}