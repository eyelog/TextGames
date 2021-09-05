package ru.eyelog.textgames.fragments.fragment03

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.QuoteSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.tvText
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.base.BaseFragment
import ru.eyelog.textgames.models.TextFormatModel

@AndroidEntryPoint
class SpannableFragment : BaseFragment() {

    private val viewModel: SpannableViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)

        viewModel.textSourceLiveData.observe(viewLifecycleOwner, {
            // Для работы ClickableSpan
            tvText.movementMethod = LinkMovementMethod.getInstance()
            // Расцветка clickable текста
            tvText.setLinkTextColor(
                ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_pressed),
                        intArrayOf(android.R.attr.state_enabled)
                    ),
                    intArrayOf(
                        Color.MAGENTA,
                        Color.BLUE
                    )
                )
            )
            tvText.text = getBigSpannableCase(it)
        })

        viewModel.colorLiveData.observe(viewLifecycleOwner, {
            tvText.setTextColor(it)
        })

        viewModel.fontLiveData.observe(viewLifecycleOwner, {
            val face = ResourcesCompat.getFont(requireContext(), it)
            tvText.typeface = face
        })

        viewModel.sizeLiveData.observe(viewLifecycleOwner, {
            tvText.textSize = it
        })
    }

    override fun setTextFormatData(data: TextFormatModel) {
        viewModel.setTextFormat(data)
    }

    private fun getBigSpannableCase(inData: String): Spannable {
        return SpannableStringBuilder(inData).apply {
            setSpan(
                StyleSpan(Typeface.BOLD),
                45,
                55,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(Color.RED),
                48,
                65,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                RelativeSizeSpan(1.5f),
                119,
                128,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                BackgroundColorSpan(Color.LTGRAY),
                134,
                155,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                UnderlineSpan(),
                157,
                170,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                URLSpan("https://developer.android.com"),
                84,
                101,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Toast.makeText(widget.context, "The text tapped", Toast.LENGTH_SHORT).show()
                    }
                },
                157,
                170,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            ContextCompat.getDrawable(requireContext(), R.drawable.smooth_dog)?.let { image ->
                image.setBounds(0, 0, 105, 100)
                setSpan(
                    ImageSpan(
                        image,
                        ImageSpan.ALIGN_BASELINE
                    ),
                    302,
                    303,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            setSpan(
                QuoteSpan(Color.GREEN, 10, 10),
                450,
                880,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}
