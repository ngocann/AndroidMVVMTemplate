package com.smartcontrol.smartcontrol.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.smartcontrol.smartcontrol.databinding.RvItemTwitBinding
import com.smartcontrol.smartcontrol.model.Board

class BoardAdapter (private var items : List<Board>,
                    private var listener: OnItemClickListener): RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    var onItemLongClickListener : OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemTwitBinding.inflate(inflater,parent, false )
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, onItemLongClickListener)
    fun setNewData(it: List<Board>) {
        items = it
        notifyDataSetChanged()
    }
    fun getItem(index: Int) : Board = items[index]

    class ViewHolder(private val binding : RvItemTwitBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board, listener: OnItemClickListener?, onItemLongClickListener: OnItemLongClickListener?) {
            binding.board = board
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
            if (onItemLongClickListener != null) {
                binding.root.setOnLongClickListener ({ _ -> onItemLongClickListener.onItemLongClick(layoutPosition) })
            }
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int) : Boolean
    }
}