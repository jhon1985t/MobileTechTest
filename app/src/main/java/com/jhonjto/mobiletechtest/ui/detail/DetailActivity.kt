package com.jhonjto.mobiletechtest.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.jhonjto.mobiletechtest.R
import com.jhonjto.mobiletechtest.databinding.ActivityDetailBinding
import com.jhonjto.mobiletechtest.ui.common.startActivity
import com.jhonjto.mobiletechtest.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val COMMENT = "DetailActivity:comment"
    }

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: PostCommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PostCommentsAdapter()
        binding.recycler.adapter = adapter

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.modelPost.observe(this, Observer(::updatePostComments))

        binding.commentDetailFavorite.setOnClickListener {
            viewModel.onFavoriteClicked()
            startActivity<MainActivity> { }
        }
        binding.deleteComment.setOnClickListener {
            viewModel.onDeleteClicked()
            startActivity<MainActivity> { }
        }
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(binding) {
        val commentsItem = model.commentsItem
        commentTitle.text = commentsItem.title
        commentBody.text = commentsItem.body

        val icon = if (commentsItem.favorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
        commentDetailFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, icon))
    }

    private fun updatePostComments(model: DetailViewModel.UiPostComments) = with(binding) {
        val postCommentsItem = model.postCommentsItem

        adapter.postCommentsItem = postCommentsItem
    }
}
