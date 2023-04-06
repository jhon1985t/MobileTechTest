package com.jhonjto.mobiletechtest.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.jhonjto.domain.CommentsItem

class CommentDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setComment(commentsItem: CommentsItem) = with(commentsItem) {
        text = buildSpannedString {

            bold { append("Title: ") }
            appendLine(title)

            bold { append("Body: ") }
            appendLine(body)
        }
    }
}
