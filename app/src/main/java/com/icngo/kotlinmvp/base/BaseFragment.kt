package com.icngo.kotlinmvp.base

import android.widget.Toast
import cn.nekocode.kotgo.component.ui.KtFragment

/**
 * Created by Administrator on 2017/4/28 0028.
 */
abstract class BaseFragment: KtFragment(),BaseView {

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}