package com.jhonjto.mobiletechtest.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jhonjto.mobiletechtest.databinding.ActivityMainBinding
import com.jhonjto.mobiletechtest.ui.common.startActivity
import com.jhonjto.mobiletechtest.ui.detail.DetailActivity
import com.jhonjto.mobiletechtest.ui.main.MainViewModel.UiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CommentsAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CommentsAdapter(viewModel::onCommentClicked)
        binding.recycler.adapter = adapter
        binding.fabDeleteAll.setOnClickListener {
            viewModel.onDeleteAllClicked()
            startActivity<MainActivity> {  }
        }
    }

    private fun updateUi(model: UiModel) {
        binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> adapter.setData(model.commentsItems)
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.COMMENT, model.commentsItem.id)
            }
            UiModel.RequestComments -> viewModel.onCallComments()
            else -> {
                Exception()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        viewModel.model.observe(this, Observer(::updateUi))
        adapter.notifyDataSetChanged()
    }
}
