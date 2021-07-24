package com.fara.morty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.fara.morty.R
import com.fara.morty.data.model.RickMorty
import com.fara.morty.databinding.ItemPreviewBinding

class CharacterAdapter() :
    PagingDataAdapter<RickMorty, CharacterAdapter.ImageViewHolder>(DiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_preview, parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by viewBinding(ItemPreviewBinding::bind)

        fun bind(rickMorty: RickMorty?) {
            with(binding) {
                description.text = "${rickMorty?.name}"
                Glide.with(poster.context)
                    .load(rickMorty?.image)
                    .into(poster)

                root.setOnClickListener {
                    onItemClickListener?.let {
                        if (rickMorty != null) {
                            it(layoutPosition, rickMorty)
                        }
                    }
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Int, RickMorty) -> Unit) {
        onItemClickListener = listener
    }
}

private var onItemClickListener: ((Int, RickMorty) -> Unit)? = null

private object DiffItemCallback : DiffUtil.ItemCallback<RickMorty>() {
    override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
        return oldItem == newItem
    }
}