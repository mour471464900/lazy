package com.haohao.lazy.english.lazyenglish.ui.activity

import com.haohao.lazy.english.lazyenglish.R
import com.haohao.lazy.english.lazyenglish.ui.fargment.InputFragment


/**
 *
 */
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
       val fragment = InputFragment()
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment,fragment,fragment!!::class.java.simpleName)
                .show(fragment).commit()
    }
}