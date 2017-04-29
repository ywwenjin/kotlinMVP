package com.icngo.kotlinmvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icngo.kotlinmvp.R
import com.icngo.kotlinmvp.base.BaseFragment
import com.icngo.kotlinmvp.contract.DetailFragmentContract
import com.icngo.kotlinmvp.model.MeiziVO
import com.icngo.kotlinmvp.presenter.DetailFragmentPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_page2.*
import org.jetbrains.anko.onClick

/**
 * Created by Administrator on 2017/4/29 0029.
 */
class DetailFragment: BaseFragment(),DetailFragmentContract.view {

    var presenter: DetailFragmentContract.persenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_page2,container,false)
    }

    override fun showMeizi(meiziVO: MeiziVO) {
        toolbar.title = meiziVO.title
        Picasso.with(activity).load(meiziVO.picUrl).centerCrop().fit().into(imageView)
        imageView.onClick {
            presenter?.onImageClick(meiziVO)
        }
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(DetailFragmentPresenter::class.java)
    }
}