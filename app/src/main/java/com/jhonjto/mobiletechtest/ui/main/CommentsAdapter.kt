package com.jhonjto.mobiletechtest.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.R
import com.jhonjto.mobiletechtest.databinding.ViewCommentBinding
import com.jhonjto.mobiletechtest.ui.common.basicDiffUtil
import com.jhonjto.mobiletechtest.ui.common.inflate

class CommentsAdapter(private val listener: (CommentsItem) -> Unit) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    var commentsItem: List<CommentsItem> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_comment, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = commentsItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentsItem[position]
        holder.bind(comment)
        holder.itemView.setOnClickListener { listener(comment) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewCommentBinding.bind(view)
        fun bind(commentsItem: CommentsItem) = with(binding) {
            tvTitle.text = commentsItem.title
            tvBody.text = commentsItem.body
        }
    }
}
