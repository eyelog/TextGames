package ru.eyelog.textgames.fragments.fragment04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_flags.fabMinus
import kotlinx.android.synthetic.main.fragment_flags.fabPlus
import kotlinx.android.synthetic.main.fragment_flags.rvFlags
import ru.eyelog.textgames.R
import ru.eyelog.textgames.fragments.base.BaseFragment
import ru.eyelog.textgames.models.TextFormatModel
import javax.inject.Inject

@AndroidEntryPoint
class SpanFlagsFragment : BaseFragment() {

    private val viewModel: SpanFlagsViewModel by activityViewModels()

    @Inject lateinit var adapter: SpanFlagsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)

        rvFlags.adapter = adapter
        rvFlags.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(requireContext().resources.getDrawable(R.drawable.divider_drawable))
        rvFlags.addItemDecoration(dividerItemDecoration)

        viewModel.flagsLiveData.observe(viewLifecycleOwner, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })

        fabPlus.setOnClickListener {
            viewModel.togglePlus()
        }

        fabMinus.setOnClickListener {
            viewModel.toggleMinus()
        }
    }

    override fun setTextFormatData(data: TextFormatModel) {
        viewModel.setTextFormat(data)
    }
}