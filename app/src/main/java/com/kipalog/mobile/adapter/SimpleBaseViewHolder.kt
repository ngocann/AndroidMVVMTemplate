package com.kipalog.mobile.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

 class SimpleBaseViewHolder <T, V : ViewDataBinding> constructor(private val viewDataBinding : V) : RecyclerView.ViewHolder(viewDataBinding.root) {

     fun bind(t : T, variable : Int, listener: OnItemClickListener? = null) {
         viewDataBinding.setVariable(variable, t)
         listener?.let {
             viewDataBinding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
         }
         viewDataBinding.executePendingBindings()
     }

     interface OnItemClickListener {
         fun onItemClick(position: Int)
     }
}