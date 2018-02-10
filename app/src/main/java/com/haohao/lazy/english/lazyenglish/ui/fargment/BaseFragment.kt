package com.haohao.lazy.english.lazyenglish.ui.fargment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.greenrobot.eventbus.EventBus

/**
 *
 */
abstract class BaseFragment : Fragment() {

    companion object {
        val TAG = "BaseFragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutId(),container,false)
    }

    abstract fun getLayoutId(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        requestData()
    }

    open fun initView(){

    }

    open fun  requestData(){

    }

    fun showToast(content:String){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show()
    }


    override fun onStart() {
        super.onStart()
        if(!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
    }

}