package com.kipalog.mobile.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class SimpleBaseAdapter<T, V : ViewDataBinding> (private var items : List<T>, private val onItemClickListener: SimpleBaseViewHolder.OnItemClickListener? = null) : RecyclerView.Adapter<SimpleBaseViewHolder<T, V>>() {

    abstract fun layoutId() : Int
    abstract fun variableBinding() : Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleBaseViewHolder<T, V> {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding : V = DataBindingUtil.inflate(inflater,layoutId(), parent, false )
        return SimpleBaseViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: SimpleBaseViewHolder<T, V>, position: Int) {
        val item = items[position]
        holder.bind(item, variableBinding(), onItemClickListener)
    }
}