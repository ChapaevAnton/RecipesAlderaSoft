package com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.ItemImageBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImagesHolder>() {

    var imagesUrl: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder =
        ImagesHolder(parent)

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        holder.bind(imagesUrl[position])
    }

    override fun getItemCount(): Int = imagesUrl.size

    class ImagesHolder private constructor(
        private val binding: ItemImageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(parent: ViewGroup): ImagesHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemImageBinding.inflate(inflater, parent, false)
                return ImagesHolder(binding)
            }
        }

        fun bind(imageUrl: String?) {
            imageUrl?.let {
                with(binding) {
                    Glide.with(root).load(imageUrl).centerCrop().into(image)
                }
            }
        }
    }
}