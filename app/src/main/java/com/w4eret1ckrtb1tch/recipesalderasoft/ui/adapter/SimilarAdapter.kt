package com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.ItemSimilarBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.SimilarEntity

class SimilarAdapter(private val clickRecipe: (uuidRecipe: String?) -> Unit) :
    RecyclerView.Adapter<SimilarAdapter.SimilarHolder>() {

    var similar: List<SimilarEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder =
        SimilarHolder(parent,clickRecipe)

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        holder.bind(similar[position])
    }

    override fun getItemCount(): Int = similar.size

    class SimilarHolder private constructor(
        private val binding: ItemSimilarBinding,
        private val clickRecipe: (uuidRecipe: String?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                parent: ViewGroup,
                clickRecipe: (uuidRecipe: String?) -> Unit
            ): SimilarHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemSimilarBinding.inflate(inflater, parent, false)
                return SimilarHolder(binding, clickRecipe)
            }
        }

        fun bind(similar: SimilarEntity?) {
            similar?.let {
                with(binding) {
                    Glide.with(root).load(similar.image).centerCrop().into(image)
                    name.text = similar.name
                    root.setOnClickListener { clickRecipe(similar.uuid) }
                }
            }
        }
    }
}