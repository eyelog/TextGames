package ru.eyelog.textgames.timehold

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import androidx.core.content.ContextCompat
import ru.eyelog.textgames.R

lateinit var context: Context

private fun getSpannableString(): SpannableString {
    val source = context.resources.getString(R.string.first_item) + "  "
    val spannableString = SpannableString(source)

    ContextCompat.getDrawable(context, R.drawable.icon)?.let { image ->
        image.setBounds(0, 0, image.intrinsicWidth, image.intrinsicHeight)
        spannableString.setSpan(
            ImageSpan(
                image,
                ImageSpan.ALIGN_BASELINE
            ),
            source.length - 1,
            source.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    return spannableString
}