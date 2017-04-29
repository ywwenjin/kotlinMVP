package com.icngo.kotlinmvp.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import cn.nekocode.kotgo.component.ui.KtPresenter
import cn.nekocode.kotgo.sample.data.DO.Meizi
import com.icngo.kotlinmvp.contract.DetailFragmentContract
import com.icngo.kotlinmvp.model.MeiziVO
import com.icngo.kotlinmvp.ui.fragment.DetailFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Administrator on 2017/4/29 0029.
 */
class DetailFragmentPresenter(): KtPresenter<DetailFragmentContract.view>(),DetailFragmentContract.persenter {

    companion object {
        const val KEY_ARG_MEIZI = "KEY_ARG_MEIZI"
        const val KEY_RLT_MEIZI = "KEY_RLT_MEIZI"

        fun pushForResult(persenter: KtPresenter<*>,requestCode: Int,meizi: Meizi,tag: String = DetailFragment::class.java.canonicalName) {
            val args = Bundle();
            args.putParcelable(KEY_ARG_MEIZI, meizi)
            persenter.pushForResult(requestCode,tag,DetailFragment::class.java,args);
        }
    }

    var view: DetailFragmentContract.view? = null

    override fun onImageClick(meiziVO: MeiziVO) {
        val meizi = (meiziVO.data ?: return)
        if (meizi is Parcelable) {
            val rlt = Intent()
            rlt.putExtra(KEY_RLT_MEIZI, meizi)
            setResult(Activity.RESULT_OK, rlt)
            popThis()
        }
    }

    override fun onViewCreated(view: DetailFragmentContract.view?, savedInstanceState: Bundle?) {
       this.view = view

        val meizi = arguments.getParcelable<Meizi>(KEY_ARG_MEIZI)

        Observable.just(meizi)
                .map {
                    MeiziVO(it.id,it.url,it)
                }
                .bindLifecycle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view?.showMeizi(it)
                }

    }
}