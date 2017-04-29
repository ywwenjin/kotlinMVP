package com.icngo.kotlinmvp.ui


import android.os.Bundle
import cn.nekocode.kotgo.component.ui.KtFragmentActivity

import com.icngo.kotlinmvp.R
import com.icngo.kotlinmvp.ui.fragment.MainFragment

class MainActivity : KtFragmentActivity() {
    override fun onCreatePresenter(presenterFactory: PresenterFactory) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(null)
        if(savedInstanceState == null){
            push(MainFragment::class.java.canonicalName,MainFragment::class.java)
        }
    }
}
