package ru.eyelog.textgames.fragments.fragment01

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.tvText
import kotlinx.android.synthetic.main.fragment_pager.viewPager
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.base.BaseFragment
import ru.eyelog.textgames.models.StyleDataText
import ru.eyelog.textgames.models.TextFormatModel
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : BaseFragment() {

    private val viewModel: SecondViewModel by activityViewModels()

    @Inject lateinit var pagerTextAdapter: PagerTextAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)

        viewModel.textListSourceLiveData.observe(viewLifecycleOwner, { dataList ->
            dataList?.let {
                pagerTextAdapter.onMeasureListener = { index ->
                    if (it.shouldSeparateText) {
                        viewModel.setCurrentLastIndex(index)
                    }
                }
                pagerTextAdapter.setData(it.text)
                viewPager.adapter = pagerTextAdapter
            }
        })

        viewModel.colorLiveData.observe(viewLifecycleOwner, {
            pagerTextAdapter.setTextStyle(StyleDataText(textColor = it))
            viewPager.adapter = pagerTextAdapter
        })

        viewModel.fontLiveData.observe(viewLifecycleOwner, {
            pagerTextAdapter.setTextStyle(StyleDataText(textFont = it))
            viewPager.adapter = pagerTextAdapter
        })

        viewModel.sizeLiveData.observe(viewLifecycleOwner, {
            pagerTextAdapter.setTextStyle(StyleDataText(textSize = it))
            viewPager.adapter = pagerTextAdapter
        })
    }

    override fun setTextFormatData(data: TextFormatModel) {
        viewModel.setTextFormat(data)
    }
}
