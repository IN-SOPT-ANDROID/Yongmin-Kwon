package org.sopt.sample.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.remote.entity.reqres.ResponseReqresDTO
import org.sopt.sample.databinding.ItemReqresBinding

class ReqresAdapter : ListAdapter<ResponseReqresDTO.Data, ReqresAdapter.ReqresViewHolder>(ReqresComparator()) {
    class ReqresViewHolder(binding: ItemReqresBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseReqresDTO.Data) {
            //TODO
        }
    }

    class ReqresComparator() : DiffUtil.ItemCallback<ResponseReqresDTO.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseReqresDTO.Data,
            newItem: ResponseReqresDTO.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseReqresDTO.Data,
            newItem: ResponseReqresDTO.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReqresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReqresBinding.inflate(layoutInflater, parent, false)
        return ReqresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReqresViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}