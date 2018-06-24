package com.smartcontrol.smartcontrol.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.smartcontrol.smartcontrol.databinding.RvItemRelayBinding
import com.smartcontrol.smartcontrol.databinding.RvItemTwitBinding
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.model.Twit

class RelayAdapter (private var items : List<Relay>): RecyclerView.Adapter<RelayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemRelayBinding.inflate(inflater,parent, false )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])
    fun setNewData(it: List<Relay>) {
        items = it
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding : RvItemRelayBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(relay:  Relay) {
            binding.relay = relay
            binding.executePendingBindings()
        }
    }
}