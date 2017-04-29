package com.icngo.kotlinmvp.contract

import android.support.v7.widget.RecyclerView
import com.icngo.kotlinmvp.base.BaseView

/**
 * Created by Administrator on 2017/4/28 0028.
 */

interface MainFragmentContract {

    interface View : BaseView {
        fun setupAdapter(adaper: RecyclerView.Adapter<*>)
    }

    interface Presenter {

    }
}