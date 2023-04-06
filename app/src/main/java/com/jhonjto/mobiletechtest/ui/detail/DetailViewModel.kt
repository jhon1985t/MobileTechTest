package com.jhonjto.mobiletechtest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.CommentsItem
import com.jhonjto.mobiletechtest.ui.common.ScopedViewModel
import com.jhonjto.usecases.FindCommentById
import com.jhonjto.usecases.ToggleCommentFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("id") private val id: Int,
    private val findCommentById: FindCommentById,
    private val toggleMovieFavorite: ToggleCommentFavorite
): ScopedViewModel() {

    class UiModel(val commentsItem: CommentsItem)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findComment()
            return _model
        }

    private fun findComment() = launch {
        _model.value = UiModel(findCommentById.invoke(id))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.commentsItem?.let {
            _model.value = UiModel(toggleMovieFavorite.invoke(it))
        }
    }
}
