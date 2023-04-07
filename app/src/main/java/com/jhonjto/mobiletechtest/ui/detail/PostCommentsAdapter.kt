package com.jhonjto.mobiletechtest.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.domain.PostCommentsItem
import com.jhonjto.mobiletechtest.R
import com.jhonjto.mobiletechtest.databinding.ViewPostsCommentsBinding
import com.jhonjto.mobiletechtest.ui.common.basicDiffUtil
import com.jhonjto.mobiletechtest.ui.common.inflate

class PostCommentsAdapter : RecyclerView.Adapter<PostCommentsAdapter.ViewHolder>() {

    var postCommentsItem: List<PostCommentsItem> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_posts_comments, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = postCommentsItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = postCommentsItem[position]
        holder.bind(comment)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewPostsCommentsBinding.bind(view)
        fun bind(postCommentsItem: PostCommentsItem) = with(binding) {
            commentDescription.text = postCommentsItem.email
            commentDescription.text = postCommentsItem.name
            commentDescription.text = postCommentsItem.body
            commentDescription.setPostComment(postCommentsItem)
        }
    }
}
