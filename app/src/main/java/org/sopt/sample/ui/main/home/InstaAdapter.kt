package org.sopt.sample.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemInstagramBinding
import org.sopt.sample.databinding.ItemTitleBinding

class InstaAdapter : RecyclerView.Adapter<InstaViewHolder<*>>() {
    var instaList: List<InstagramData> = emptyList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstaViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(viewType){
            0->{
                val titleBinding = ItemTitleBinding.inflate(layoutInflater, parent, false)
                InstaTitleViewHolder(titleBinding)
            }
            else->{
                val contentBinding = ItemInstagramBinding.inflate(layoutInflater, parent, false)
                InstaContentViewHolder(contentBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: InstaViewHolder<*>, position: Int) {
        val item = instaList[position]
        when(holder){
            is InstaTitleViewHolder -> holder.bind(item as InstagramTitle)
            is InstaContentViewHolder -> holder.bind(item as InstagramContent)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = instaList.size

    override fun getItemViewType(position: Int): Int {
        return when (instaList[position].isTitle) {
            true -> 0
            else -> 1
        }
    }
}