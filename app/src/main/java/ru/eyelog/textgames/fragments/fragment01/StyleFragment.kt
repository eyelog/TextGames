package ru.eyelog.textgames.fragments.fragment01

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_style.*
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.base.BaseFragment
import ru.eyelog.textgames.models.TextFormatModel

@AndroidEntryPoint
class StyleFragment : BaseFragment() {

    private val viewModel: StyleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)

        viewModel.textSourceLiveData.observe(viewLifecycleOwner, {
            tvSimpleText.text = "Without any strategy\n $it"
            tvBreakStrategySimpleText.text = "Simple break strategy\n $it"
            tvBreakStrategyBalancedText.text = "Balanced break strategy\n $it"
            tvHyphFreqNormalText.text = "Hyphenation Frequency normal strategy\n $it"
            tvHyphFreqFullText.text = "Hyphenation Frequency full strategy\n $it"
            tvHyphFreqFullText.setTextAppearance(R.style.TextAppearanceSample)
            tvMultyText.setTextAppearance(R.style.TextAppearanceSample)
            val spannedText = SpannableString("Multy strategy\n $it")
            spannedText.setSpan(
                ForegroundColorSpan(Color.MAGENTA),
                0,
                spannedText.lastIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvMultyText.text = spannedText
            val typeface = resources.getFont(R.font.andantino)
            tvMultyText.setTypeface(typeface, Typeface.BOLD)
        })

        viewModel.sizeLiveData.observe(viewLifecycleOwner, {
            tvSimpleText.textSize = it
            tvBreakStrategySimpleText.textSize = it
            tvBreakStrategyBalancedText.textSize = it
            tvHyphFreqNormalText.textSize = it
            tvHyphFreqFullText.textSize = it
            tvMultyText.textSize = it
        })
    }

    override fun setTextFormatData(data: TextFormatModel) {
        viewModel.setTextFormat(data)
    }
}