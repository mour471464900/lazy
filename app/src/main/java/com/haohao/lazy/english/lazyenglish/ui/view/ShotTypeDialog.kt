package com.haohao.lazy.english.lazyenglish.ui.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.haohao.lazy.english.lazyenglish.R
import com.haohao.lazy.english.lazyenglish.utils.EventBusUtil
import com.haohao.lazy.english.lazyenglish.utils.SizeUtil
import kotlinx.android.synthetic.main.dialog_choice.*
import org.greenrobot.eventbus.EventBus

/**
 *  选择发布方式
 */
class ShotTypeDialog : Dialog, View.OnClickListener {
    constructor(context: Context) : super(context, R.style.base_dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View.inflate(context, R.layout.dialog_choice, null))
        val window = window
        val layoutParams = window.attributes
        layoutParams.width = SizeUtil.dip2px(context, 328f)
        layoutParams.height = SizeUtil.dip2px(context, 283f)
        window.attributes = layoutParams
        tv_one.setOnClickListener(this)
        tv_two.setOnClickListener(this)
        setCanceledOnTouchOutside(false)
    }


    override fun onClick(v: View?) {
        dismiss()
        when (v) {
            tv_one -> postEssayChicoe(0)
            tv_two -> postEssayChicoe(1)
        }
    }

    fun postEssayChicoe(type: Int) {
        var stringArray = context.resources.getStringArray(R.array.content)
        EventBus.getDefault().post(EventBusUtil.EssayChicoe(stringArray[type]))
    }
}