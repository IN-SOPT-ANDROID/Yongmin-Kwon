package org.sopt.sample.ui.main.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemInstagramContentBinding
import org.sopt.sample.databinding.ItemInstagramTitleBinding

abstract class InstagramViewHolder(binding : View) : RecyclerView.ViewHolder(binding) {
    abstract fun bind(data : InstagramData)
}

class InstagramContentViewHolder(private val binding : ItemInstagramContentBinding) : InstagramViewHolder(binding.root){
    override fun bind(data: InstagramData) {
        val contentData = data as InstagramContent
        binding.textInstaName.text = contentData.name
        binding.textInstaId.text = contentData.id
    }
}

class InstagramTitleViewHolder(private val binding : ItemInstagramTitleBinding) : InstagramViewHolder(binding.root){
    override fun bind(data: InstagramData) {
        val titleData = data as InstagramTitle
        binding.textInstaTitle.text = titleData.title
    }
}