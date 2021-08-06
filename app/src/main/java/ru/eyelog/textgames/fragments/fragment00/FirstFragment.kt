package ru.eyelog.textgames.fragments.fragment00

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.tvText
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.DataMaster
import ru.eyelog.textgames.fragments.base.BaseFragment
import ru.eyelog.textgames.models.TransportModel

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

        viewModel.sampleLiveData.observe(viewLifecycleOwner, {
            tvText.text = it
        })
    }

    override fun setTextData(data: TransportModel) {
        Log.i("Logcat ", "$data")
    }
}
