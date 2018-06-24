package com.smartcontrol.smartcontrol.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.smartcontrol.smartcontrol.databinding.RvItemTwitBinding
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit

class BoardAdapter (private var items : List<Board>): RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemTwitBinding.inflate(inflater,parent, false )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])
    fun setNewData(it: List<Board>) {
        items = it
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding : RvItemTwitBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(board:  Board) {
            binding.board = board
            binding.executePendingBindings()
        }
    }
}