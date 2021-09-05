package ru.eyelog.textgames.fragments.fragment04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eyelog.textgames.R
import javax.inject.Inject

class SpanFlagsAdapter @Inject constructor() : RecyclerView.Adapter<SpanFlagsAdapter.SpanFlagsHolder>() {

    private var data: List<FlagsPair> = emptyList()

    fun setData(data: List<FlagsPair>) {
        this.data = data
    }

    inner class SpanFlagsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var tvContent: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvContent = itemView.findViewById(R.id.tvContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpanFlagsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flags, parent, false)
        return SpanFlagsHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpanFlagsHolder, position: Int) {
        holder.tvTitle?.text = data[position].title
        holder.tvContent?.text = data[position].content
    }

    override fun getItemCount() = data.size
}