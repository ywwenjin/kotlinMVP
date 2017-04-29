package com.icngo.kotlinmvp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.nekocode.kotgo.component.rx.RxBus
import com.icngo.kotlinmvp.R
import com.icngo.kotlinmvp.base.BaseFragment
import com.icngo.kotlinmvp.contract.MainFragmentContract
import com.icngo.kotlinmvp.event.LoadFinishedEvent
import com.icngo.kotlinmvp.presenter.MainFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Administrator on 2017/4/29 0029.
 */
class MainFragment : BaseFragment(),MainFragmentContract.View {

    var presenter: MainFragmentContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.toObserverable(LoadFinishedEvent::class.java)
                .bindLifecycle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toolbar.title = "Meizi List - Load finished"
                }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_main,container,false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = "Meizi List"
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun setupAdapter(adaper: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adaper
    }

    override fun onCreatePresenter(presenterFactory: PresenterFactory) {
        presenter = presenterFactory.createOrGet(MainFragmentPresenter::class.java)
    }

    override fun onBackPressed(): Boolean {
        toast("finish")
        return super.onBackPressed()
    }
}