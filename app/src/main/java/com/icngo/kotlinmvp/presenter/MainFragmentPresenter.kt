package com.icngo.kotlinmvp.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import cn.nekocode.kotgo.component.rx.RxBus
import cn.nekocode.kotgo.component.ui.KtPresenter
import cn.nekocode.kotgo.sample.data.DO.Meizi
import cn.nekocode.kotgo.sample.data.repo.MeiziRepo
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver
import com.icngo.kotlinmvp.adapter.MeiziListAdapter
import com.icngo.kotlinmvp.contract.MainFragmentContract
import com.icngo.kotlinmvp.event.LoadFinishedEvent
import com.icngo.kotlinmvp.ui.fragment.DetailFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Administrator on 2017/4/28 0028.
 */
class MainFragmentPresenter():KtPresenter<MainFragmentContract.View>(),MainFragmentContract.Presenter {

    companion object {
        const val REQUEST_CODE_PAGE2 = 1;
    }

    var view: MainFragmentContract.View? = null;

    @State var meiziList = ArrayList<Meizi>()

    val adapter = MeiziListAdapter(meiziList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)

        Observable.just(meiziList)
                .flatMap {
                    if(it.size<=0){
                        MeiziRepo.getMeizis(50, 1)
                    }else{
                        Observable.just(it)
                    }
                }
                .bindLifecycle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    meiziList.clear()
                    meiziList.addAll(it)
                    adapter.notifyDataSetChanged()
                    RxBus.send(LoadFinishedEvent())
                }
    }

    override fun onViewCreated(view: MainFragmentContract.View?, savedInstanceState: Bundle?) {
        this.view = view;
        adapter.let {
            view?.setupAdapter(adapter)
            it.onMeiziItemClickListener = {
                meizi ->  DetailFragmentPresenter.pushForResult(this,REQUEST_CODE_PAGE2,meizi)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState ?: return)
    }

    override fun onResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_PAGE2 -> {
                if(resultCode == Activity.RESULT_OK && data !=null){
                    val relMeizi = data.getParcelableExtra<Meizi>(DetailFragmentPresenter.KEY_RLT_MEIZI)
                    view?.toast("You clicked the photo: ${relMeizi.id}")
                }
            }
        }
    }
}