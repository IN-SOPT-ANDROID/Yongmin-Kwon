package org.sopt.sample.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemInstagramContentBinding
import org.sopt.sample.databinding.ItemInstagramTitleBinding
import org.sopt.sample.ui.main.home.InstagramData.Companion.CONTENT
import org.sopt.sample.ui.main.home.InstagramData.Companion.TITLE

class InstagramAdapter : RecyclerView.Adapter<InstagramViewHolder>() {
    val instagramList = mutableListOf<InstagramData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstagramViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val titleBinding = ItemInstagramTitleBinding.inflate(layoutInflater)
        val contentBinding = ItemInstagramContentBinding.inflate(layoutInflater)

        return when (viewType) {
            TITLE -> InstagramTitleViewHolder(titleBinding)
            CONTENT -> InstagramContentViewHolder(contentBinding)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: InstagramViewHolder, position: Int) {
        val item = instagramList[position]
        when (holder) {
            is InstagramTitleViewHolder -> holder.bind(item)
            is InstagramContentViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int = instagramList.size

    override fun getItemViewType(position: Int): Int {
        return instagramList[position].viewType
    }
}