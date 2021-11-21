package com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.FragmentListRecipesBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.Result
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.factories.ViewModelFactory
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.ListRecipesViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.RecipesAdapter
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.SpacingItemDecoration
import dagger.Lazy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListRecipesFragment : DaggerFragment(R.layout.fragment_list_recipes) {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelFactory>
    private val viewModel by viewModels<ListRecipesViewModel>(factoryProducer = { viewModelFactory.get() })
    private var binding: FragmentListRecipesBinding? = null
    private val decorator by lazy { SpacingItemDecoration(6, 6) }
    private lateinit var adapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecipesAdapter { uuidRecipe -> Log.d("TAG", "clickRecipe: $uuidRecipe") }
        binding?.apply {
            listRecipes.addItemDecoration(decorator)
            listRecipes.adapter = adapter
        }
        viewModel.getRecipes.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> resolveSuccess(it.value)
                is Result.Failure -> resolveFailure(it.exception)
                Result.Loading -> resolveLoading()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun resolveSuccess(recipes: List<RecipeEntity>) {
        adapter.recipes = recipes
        binding?.apply {
            listRecipes.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun resolveFailure(exception: Throwable?) {
        showDescription(exception?.message.toString())
        binding?.apply {
            listRecipes.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun resolveLoading() {
        binding?.apply {
            listRecipes.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showDescription(description: String) {
        val snackBar = Snackbar.make(binding?.root!!, description, Snackbar.LENGTH_INDEFINITE)
        snackBar
            .setMaxInlineActionWidth(resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width))
            .setAction(R.string.retry) { viewModel.loadRecipes(); snackBar.dismiss() }
            .show()
    }
}
