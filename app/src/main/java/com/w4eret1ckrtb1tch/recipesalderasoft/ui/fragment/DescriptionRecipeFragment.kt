package com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.FragmentDescriptionRecipeBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.Result
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.factories.ViewModelFactory
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.DescriptionRecipeViewModel
import dagger.Lazy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DescriptionRecipeFragment : DaggerFragment(R.layout.fragment_description_recipe) {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelFactory>
    private val viewModel by viewModels<DescriptionRecipeViewModel>(factoryProducer = { viewModelFactory.get() })
    private val args: DescriptionRecipeFragmentArgs by navArgs()
    private var recipeUuid: String? = null
    private var binding: FragmentDescriptionRecipeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionRecipeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeUuid = args.uuidRecipe
        viewModel.loadRecipeDescription(recipeUuid)
        binding?.apply {

        }

        viewModel.getRecipeDescription.observe(viewLifecycleOwner) {
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

    private fun resolveSuccess(recipe: RecipeEntity) {
        binding?.apply {
            progressBar.visibility = View.GONE
            name.text = recipe.name
            difficulty.text = recipe.difficulty.toString()
            description.text = recipe.description
            instruction.text = recipe.instructions
        }
    }

    private fun resolveFailure(exception: Throwable?) {
        showDescription(exception?.message.toString())
        binding?.apply {
            progressBar.visibility = View.GONE
        }
    }

    private fun resolveLoading() {
        binding?.apply {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showDescription(description: String) {
        val snackBar = Snackbar.make(binding?.root!!, description, Snackbar.LENGTH_INDEFINITE)
        snackBar
            .setMaxInlineActionWidth(resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width))
            .setAction(R.string.retry) { viewModel.loadRecipeDescription(recipeUuid); snackBar.dismiss() }
            .show()
    }

}