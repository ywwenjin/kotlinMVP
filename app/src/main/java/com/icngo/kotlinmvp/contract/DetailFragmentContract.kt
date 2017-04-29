package com.icngo.kotlinmvp.contract

import com.icngo.kotlinmvp.base.BaseView
import com.icngo.kotlinmvp.model.MeiziVO

/**
 * Created by Administrator on 2017/4/29 0029.
 */
interface DetailFragmentContract {

    interface view: BaseView {
        fun showMeizi(meiziVO: MeiziVO)
    }
    interface persenter {
        fun onImageClick(meiziVO: MeiziVO)
    }
}