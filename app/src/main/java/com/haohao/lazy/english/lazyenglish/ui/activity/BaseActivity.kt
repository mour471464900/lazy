package com.haohao.lazy.english.lazyenglish.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

/**
 *
 */
abstract class BaseActivity : AppCompatActivity() {
    companion object {
        var TAG = "BaseActivity"
        var activityList : ArrayList<AppCompatActivity> = arrayListOf()
        fun finishAll()= activityList.forEach { it.finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        activityList.add(this)
        initView()
        requestData()
    }

    open fun requestData() {

    }

    open fun initView(){

    }

    /**
     *  当前布局
     */
    abstract fun getLayoutId(): Int



}