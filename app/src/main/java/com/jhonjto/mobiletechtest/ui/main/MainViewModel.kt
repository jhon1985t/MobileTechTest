package com.jhonjto.mobiletechtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.ui.common.ScopedViewModel
import com.jhonjto.usecases.GetPopularComments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPopularComments: GetPopularComments) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading: UiModel()
        class Content(val commentsItems: List<CommentsItem>): UiModel()
        class Navigation(val commentsItem: CommentsItem): UiModel()
        object RequestComments: UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        _model.value = UiModel.RequestComments
    }

    fun onCallComments() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getPopularComments.invoke())
        }
    }

    fun onCommentClicked(commentsItem: CommentsItem) {
        _model.value = UiModel.Navigation(commentsItem)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
