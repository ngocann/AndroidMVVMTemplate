package com.kipalog.mobile.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kipalog.mobile.databinding.RvItemPostBinding
import com.kipalog.mobile.databinding.RvItemTwitBinding
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Post

class PostAdapter (private var items : List<Post>,
                   private var listener: OnItemClickListener): RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemPostBinding.inflate(inflater,parent, false )
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)
    fun setNewData(it: List<Post>) {
        items = it
        notifyDataSetChanged()
    }
    fun getItem(index: Int) : Post = items[index]

    class ViewHolder(private val binding : RvItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Post, listener: OnItemClickListener?) {
            binding.post = board
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(layoutPosition) }
            }
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}