package com.jhonjto.mobiletechtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.ui.common.ScopedViewModel
import com.jhonjto.usecases.DeleteAllComments
import com.jhonjto.usecases.GetPopularComments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularComments: GetPopularComments,
    private val deleteAllComments: DeleteAllComments
    ) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _modelDelete = MutableLiveData<UiDelete>()
    val modelDelete: LiveData<UiDelete>
        get() {
            if (_modelDelete.value == null) onDeleteAllClicked()
            return _modelDelete
        }

    class UiDelete(invoke: Unit)
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

    fun onDeleteAllClicked() = launch {
        _modelDelete.value = UiDelete(deleteAllComments.invoke())
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
