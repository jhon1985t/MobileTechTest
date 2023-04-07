package com.jhonjto.mobiletechtest.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.jhonjto.domain.CommentsItem
import com.jhonjto.domain.PostCommentsItem

class CommentDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setPostComment(postCommentsItem: PostCommentsItem) = with(postCommentsItem) {
        text = buildSpannedString {

            bold { append("Email: ") }
            appendLine(email)

            bold { append("Name: ") }
            appendLine(name)

            bold { append("Body: ") }
            appendLine(body)
        }
    }
}
