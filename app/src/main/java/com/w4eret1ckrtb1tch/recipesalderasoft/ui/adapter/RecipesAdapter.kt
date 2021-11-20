package com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.ItemRecipeBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import java.util.*

class RecipesAdapter(private val clickRecipe: (uuidRecipe: UUID) -> Unit) :
    RecyclerView.Adapter<RecipesAdapter.RecipesHolder>() {

    var recipes: List<RecipeEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesHolder =
        RecipesHolder(parent, clickRecipe)

    override fun onBindViewHolder(holder: RecipesHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size


    class RecipesHolder private constructor(
        private val binding: ItemRecipeBinding,
        private val clickRecipe: (uuidRecipe: UUID) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                parent: ViewGroup,
                clickRecipe: (uuidRecipe: UUID) -> Unit
            ): RecipesHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemRecipeBinding.inflate(inflater, parent, false)
                return RecipesHolder(binding, clickRecipe)
            }
        }

        fun bind(recipe: RecipeEntity) {
            with(binding) {
                Glide.with(root).load(recipe.images?.get(0)).centerCrop().into(image)
                name.text = recipe.name
                description.text = recipe.description
                root.setOnClickListener { clickRecipe.invoke(recipe.uuid) }
            }
        }
    }
}