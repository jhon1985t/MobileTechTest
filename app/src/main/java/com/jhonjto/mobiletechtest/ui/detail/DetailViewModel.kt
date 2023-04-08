package com.jhonjto.mobiletechtest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.CommentsItem
import com.jhonjto.domain.PostCommentsItem
import com.jhonjto.mobiletechtest.ui.common.ScopedViewModel
import com.jhonjto.usecases.DeleteCommentsById
import com.jhonjto.usecases.FindCommentById
import com.jhonjto.usecases.GetPostCommentsByPostId
import com.jhonjto.usecases.ToggleCommentFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("id") private val id: Int,
    private val findCommentById: FindCommentById,
    private val getPostCommentsByPostId: GetPostCommentsByPostId,
    private val deleteCommentsById: DeleteCommentsById,
    private val toggleMovieFavorite: ToggleCommentFavorite
): ScopedViewModel() {

    class UiModel(val commentsItem: CommentsItem)
    class UiPostComments(val postCommentsItem: List<PostCommentsItem>)
    class UiDeleteComment(val id: Unit)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findComment()
            return _model
        }

    private val _modelPost = MutableLiveData<UiPostComments>()
    val modelPost: LiveData<UiPostComments>
        get() {
            if (_modelPost.value == null) findPostComments()
            return _modelPost
        }

    private val _modelDelete = MutableLiveData<UiDeleteComment>()
    val modelDelete: LiveData<UiDeleteComment>
        get() {
            if (_modelPost.value == null) onDeleteClicked()
            return _modelDelete
        }

    private fun findComment() = launch {
        _model.value = UiModel(findCommentById.invoke(id))
    }

    private fun findPostComments() = launch {
        _modelPost.value = UiPostComments(getPostCommentsByPostId.invoke(id))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.commentsItem?.let {
            _model.value = UiModel(toggleMovieFavorite.invoke(it))
        }
    }

    fun onDeleteClicked() = launch {
        _modelDelete.value = UiDeleteComment(deleteCommentsById.invoke(id))
    }
}
