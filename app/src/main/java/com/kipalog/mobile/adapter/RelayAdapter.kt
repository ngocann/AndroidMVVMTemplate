package com.kipalog.mobile.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kipalog.mobile.R
import com.kipalog.mobile.databinding.RvItemRelayBinding
import com.kipalog.mobile.databinding.RvItemTwitBinding
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Relay

class RelayAdapter (private var items : List<Relay>,
                    private var listener: RelayAdapter.OnItemClickListener): RecyclerView.Adapter<RelayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemRelayBinding.inflate(inflater,parent, false )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.count()
    fun setNewData(it: List<Relay>) {
        items = it
        notifyDataSetChanged()
    }
    fun getItem(position: Int) : Relay {
        return items[position]
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)


    class ViewHolder(private val binding : RvItemRelayBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(relay:  Relay, listener: RelayAdapter.OnItemClickListener?) {
            binding.relay = relay
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(layoutPosition) }
            }
            relay.status?.let { binding.root.isSelected = it }
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}