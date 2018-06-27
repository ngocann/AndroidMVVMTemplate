package com.smartcontrol.smartcontrol.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.smartcontrol.smartcontrol.R
import com.smartcontrol.smartcontrol.databinding.RvItemRelayBinding
import com.smartcontrol.smartcontrol.databinding.RvItemTwitBinding
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.model.Twit

class RelayAdapter (private var items : List<Relay>,
                    private var listener: BoardAdapter.OnItemClickListener): RecyclerView.Adapter<RelayAdapter.ViewHolder>() {

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
        fun bind(relay:  Relay, listener: BoardAdapter.OnItemClickListener?) {
            binding.relay = relay
            val colorOn = itemView.context.resources.getColor(R.color.relayOn)
            val colorOff = itemView.context.resources.getColor(R.color.relayOff)
            if (relay.status!!) {
                binding.tvName.setTextColor(colorOn)
            }else {
                binding.tvName.setTextColor(colorOff)
            }
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}