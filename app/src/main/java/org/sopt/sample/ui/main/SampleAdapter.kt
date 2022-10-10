package org.sopt.sample.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemSampleBinding

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {
    var sampleList : List<String> = emptyList()

    class SampleViewHolder(private val binding : ItemSampleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(text : String){
            binding.textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSampleBinding.inflate(layoutInflater, parent, false)
        return SampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(sampleList[position])
    }

    override fun getItemCount() = sampleList.size
}