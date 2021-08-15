package ru.eyelog.textgames.fragments.fragment01

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.PagerAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.fragment_main.tvText
import ru.eyelog.textgames.R
import ru.eyelog.textgames.exts.getLastVisibleCharacter
import ru.eyelog.textgames.models.StyleDataText
import javax.inject.Inject

class PagerTextAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : PagerAdapter() {

    private var stringData: List<String> = emptyList()
    var onMeasureListener: ((Int) -> Unit)? = null

    private var currentTextColor: Int? = null
    private var currentTextFont: Int? = null
    private var currentTextSize: Float? = null

    fun setData(inData: List<String>) {
        stringData = inData
    }

    fun setTextStyle(styleDataText: StyleDataText) {
        styleDataText.textColor?.let {
            currentTextColor = it
        }
        styleDataText.textFont?.let {
            currentTextFont = it
        }
        styleDataText.textSize?.let {
            currentTextSize = it
        }
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
        currentTextColor?.let {  tvText.setTextColor(it)}
        currentTextFont?.let {
            val face = ResourcesCompat.getFont(context, it)
            tvText.typeface = face
        }
        currentTextSize?.let {
            tvText.textSize = it
        }

        tvText.post {
            onMeasureListener?.invoke(tvText.getLastVisibleCharacter())

//            Log.i("Logcat", "stringData[position] ${stringData[position].substring(0, tvText.getLastVisibleCharacter())}")
        }

        container.addView(layout)

        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}
