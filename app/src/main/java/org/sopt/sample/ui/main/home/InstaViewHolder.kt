package org.sopt.sample.ui.main.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemInstagramBinding
import org.sopt.sample.databinding.ItemTitleBinding

abstract class InstaViewHolder<in T>(binding: View) :
    RecyclerView.ViewHolder(binding) {
    abstract fun bind(data: T)
}

class InstaContentViewHolder(private val binding: ItemInstagramBinding) :
    InstaViewHolder<InstagramContent>(binding.root) {
    override fun bind(data: InstagramContent) {
        binding.textInstaName.text = data.name
        binding.textInstaId.text = data.id
    }
}

class InstaTitleViewHolder(private val binding: ItemTitleBinding) :
    InstaViewHolder<InstagramTitle>(binding.root) {
    override fun bind(data: InstagramTitle) {}
}