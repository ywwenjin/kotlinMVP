package com.icngo.kotlinmvp

import android.app.Application
import android.support.multidex.MultiDexApplication
import cn.nekocode.kotgo.sample.data.DataLayer

/**
 * Created by Administrator on 2017/4/28 0028.
 */
class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DataLayer.init(this)
    }
}