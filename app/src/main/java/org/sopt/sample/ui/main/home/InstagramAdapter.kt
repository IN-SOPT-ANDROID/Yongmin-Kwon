package org.sopt.sample.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemInstagramContentBinding
import org.sopt.sample.databinding.ItemInstagramTitleBinding
import org.sopt.sample.ui.main.home.InstagramData.Companion.CONTENT
import org.sopt.sample.ui.main.home.InstagramData.Companion.TITLE

class InstagramAdapter(context: Context) : RecyclerView.Adapter<InstagramViewHolder>() {
    private val instagramList = mutableListOf<InstagramData>()
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstagramViewHolder {
        return when (viewType) {
            TITLE -> {
                val titleBinding = ItemInstagramTitleBinding.inflate(layoutInflater, parent, false)
                InstagramTitleViewHolder(titleBinding)
            }
            CONTENT -> {
                val contentBinding = ItemInstagramContentBinding.inflate(layoutInflater, parent, false)
                InstagramContentViewHolder(contentBinding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: InstagramViewHolder, position: Int) {
        holder.bind(instagramList[position])
    }

    override fun getItemCount(): Int = instagramList.size

    override fun getItemViewType(position: Int): Int {
        return instagramList[position].viewType
    }

    fun updateList(list: List<InstagramData>) {
        instagramList.addAll(list)
        notifyDataSetChanged()
    }
}