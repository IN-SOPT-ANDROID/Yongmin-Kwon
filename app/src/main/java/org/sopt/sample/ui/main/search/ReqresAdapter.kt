package org.sopt.sample.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.data.remote.entity.reqres.ResponseReqresDto
import org.sopt.sample.databinding.ItemReqresBinding

class ReqresAdapter : ListAdapter<ResponseReqresDto.Data, ReqresAdapter.ReqresViewHolder>(ReqresComparator()) {
    class ReqresViewHolder(private val binding: ItemReqresBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseReqresDto.Data) {
            Glide.with(binding.imageReqresProfile).load(data.avatar).into(binding.imageReqresProfile)
            binding.textReqresName.text = data.firstName + " " + data.lastName
            binding.textReqresEmail.text = data.email
        }
    }

    class ReqresComparator() : DiffUtil.ItemCallback<ResponseReqresDto.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseReqresDto.Data,
            newItem: ResponseReqresDto.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseReqresDto.Data,
            newItem: ResponseReqresDto.Data
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