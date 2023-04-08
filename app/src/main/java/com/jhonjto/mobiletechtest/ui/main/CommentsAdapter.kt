package com.jhonjto.mobiletechtest.ui.main

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.R
import com.jhonjto.mobiletechtest.databinding.ViewCommentBinding
import com.jhonjto.mobiletechtest.ui.common.inflate

class CommentsAdapter(private val listener: (CommentsItem) -> Unit) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private var commentsItem = emptyList<CommentsItem>()

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
            if (commentsItem.favorite) {
                tvTitle.text = commentsItem.title
                tvBody.text = commentsItem.body
                ivFavorite.visibility = View.VISIBLE
            } else {
                tvTitle.text = commentsItem.title
                tvBody.text = commentsItem.body
                ivFavorite.visibility = View.GONE
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<CommentsItem>) {
        val artistsDiffUtil = CommentsFavoritesDiffUtil(commentsItem, newData)
        val diffUtilResult = DiffUtil.calculateDiff(artistsDiffUtil)
        commentsItem = emptyList()
        commentsItem = newData
        diffUtilResult.dispatchUpdatesTo(this)
        this.notifyDataSetChanged()
    }
}
