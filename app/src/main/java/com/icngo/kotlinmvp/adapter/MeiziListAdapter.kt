package com.icngo.kotlinmvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cn.nekocode.kotgo.sample.data.DO.Meizi
import com.icngo.kotlinmvp.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.layoutInflater
import kotlinx.android.synthetic.main.item_meizi.view.*

/**
 * Created by Administrator on 2017/4/28 0028.
 */
class MeiziListAdapter(private val list:ArrayList<Meizi>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    object Type {
        const val TYPE_ITEM: Int = 0
    }

    var onMeiziItemClickListener:((Meizi) -> Unit)? = null;
    var onMeiziItemLongClickListener:((Meizi) -> Boolean)? = null;

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when(holder) {
            is ItemViewHolder -> {
                holder.setData(list[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            Type.TYPE_ITEM -> {
                val v = parent.context.layoutInflater.inflate(R.layout.item_meizi,parent,false)
                return ItemViewHolder(v)
            }
        }
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int {
        return list.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        return Type.TYPE_ITEM
    }

    private inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var meizi: Meizi? = null
        init {
            view.setOnClickListener {
                meizi?.let { onMeiziItemClickListener?.invoke(it) }
            }

            view.setOnLongClickListener {
                meizi?.let { onMeiziItemLongClickListener?.invoke(it) } ?: false
            }
        }

        fun setData(meizi: Meizi) {
            this.meizi = meizi;

            Picasso.with(itemView.context).load(meizi.url).centerCrop().fit().into(itemView.imageView)
            itemView.textView.text = meizi.id
            itemView.textView2.text = "${meizi.who} - ${meizi.type}"
        }
    }
}