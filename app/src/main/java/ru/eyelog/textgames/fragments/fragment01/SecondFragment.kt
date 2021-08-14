package ru.eyelog.textgames.fragments.fragment01

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pager.viewPager
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.base.BaseFragment
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

        viewModel.textListSourceLiveData.observe(viewLifecycleOwner, {
            pagerTextAdapter.onMeasureListener = { tac ->
                Log.i("Logcat", tac)
            }
            pagerTextAdapter.setData(it)
            viewPager.adapter = pagerTextAdapter
        })
    }

    override fun setTextData(data: TextFormatModel) {
        Log.i("Logcat", "$data")
    }
}
