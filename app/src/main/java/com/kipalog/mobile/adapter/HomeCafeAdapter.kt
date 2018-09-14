package com.kipalog.mobile.adapter

import com.android.databinding.library.baseAdapters.BR
import com.kipalog.mobile.R
import com.kipalog.mobile.databinding.RvItemHomeCafeBinding
import com.kipalog.mobile.model.Cafe

class HomeCafeAdapter(items: List<Cafe>, onItemClickListener: SimpleBaseViewHolder.OnItemClickListener? = null) : SimpleBaseAdapter<Cafe, RvItemHomeCafeBinding>(items, onItemClickListener) {
    override fun layoutId(): Int = R.layout.rv_item_home_cafe_image
    override fun variableBinding(): Int = BR.cafe
}