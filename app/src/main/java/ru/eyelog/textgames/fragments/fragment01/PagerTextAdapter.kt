package ru.eyelog.textgames.fragments.fragment01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.eyelog.textgames.R
import javax.inject.Inject

class PagerTextAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : PagerAdapter() {

    private var stringData: List<String> = emptyList()
    var onMeasureListener: ((String) -> Unit)? = null

    fun setData(inData: List<String>) {
        stringData = inData
    }

    override fun getCount(): Int {
        return stringData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.text_card_item, container, false) as ViewGroup

        val tvText = layout.findViewById<TextView>(R.id.tvItemText)
        tvText.text = stringData[position]
        tvText.post {

            val textWidth: Float = tvText.paint.measureText(stringData[position])
            val measure = tvText.length()

//            val height: Int = tvText.getHeight()
//            val scrollY: Int = tvText.getScrollY()
//            val layout: Layout = tvText.getLayout()
//
//            val firstVisibleLineNumber: Int = layout.getLineForVertical(scrollY)
//            val lastVisibleLineNumber: Int = layout.getLineForVertical(scrollY + height)

//            val start: Int = tvText.layout.getLineStart(0)
//            val end: Int = tvText.layout.getLineEnd(tvText.lineCount - 1)

            val displayed: String = tvText.text.toString().substring(0, measure)
            onMeasureListener?.invoke("textWidth $textWidth; measure $measure \n $displayed")
        }


        container.addView(layout)

        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}
