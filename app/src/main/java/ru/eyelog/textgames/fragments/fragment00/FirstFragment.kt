package ru.eyelog.textgames.fragments.fragment00

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.tvText
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.base.BaseFragment
import ru.eyelog.textgames.models.TextFormatModel

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private val viewModel: FirstViewModel by activityViewModels()

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
            tvText.text = it
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
}
