package com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.FragmentDescriptionRecipeBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.RecipeEntity
import com.w4eret1ckrtb1tch.recipesalderasoft.domain.entity.Result
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.factories.ViewModelFactory
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.DescriptionRecipeViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.ImagesAdapter
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.SimilarAdapter
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.SpacingItemDecoration
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
    private val decorator by lazy { SpacingItemDecoration(6, 0) }
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var similarAdapter: SimilarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeUuid = args.uuidRecipe
        viewModel.loadRecipeDescription(recipeUuid)
    }

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
        imagesAdapter = ImagesAdapter()
        similarAdapter = SimilarAdapter { uuidRecipe ->
            openDescriptionRecipeToDescriptionSimilarRecipe(uuidRecipe)
        }
        binding?.apply {
            images.addItemDecoration(decorator)
            images.adapter = imagesAdapter
            similar.addItemDecoration(decorator)
            similar.adapter = similarAdapter
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

    private fun openDescriptionRecipeToDescriptionSimilarRecipe(uuidRecipe: String?) {
        Log.d("TAG", "clickRecipe: $uuidRecipe")
        val action = DescriptionRecipeFragmentDirections
            .actionToDescriptionSimilarRecipe(uuidRecipe)
        findNavController().navigate(action)
    }

    private fun resolveSuccess(recipe: RecipeEntity) {
        binding?.apply {
            progressBar.visibility = View.GONE
            root.visibility = View.VISIBLE
            name.text = recipe.name ?: getString(R.string.default_no_value_text)
            difficulty.rating = recipe.difficulty?.toFloat()
                ?: resources.getDimension(R.dimen.default_no_value_float)
            description.text = recipe.description ?: getString(R.string.default_no_value_text)
            recipe.images?.let { imagesAdapter.imagesUrl = it }
            recipe.similar?.let { similarAdapter.similar = it }
            instruction.text = HtmlCompat.fromHtml(
                recipe.instructions ?: getString(R.string.default_no_value_text),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }
    }

    private fun resolveFailure(exception: Throwable?) {
        binding?.apply {
            (requireActivity() as MainActivity)
                .showDescription(
                    exception?.message.toString(),
                    root
                ) { viewModel.loadRecipeDescription(recipeUuid) }
            progressBar.visibility = View.GONE
            root.visibility = View.GONE
        }
    }

    private fun resolveLoading() {
        binding?.apply {
            progressBar.visibility = View.VISIBLE
            root.visibility = View.GONE
        }
    }
}