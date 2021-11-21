package com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.FragmentListRecipesBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.factories.ViewModelFactory
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.ListRecipesViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.RecipesAdapter
import dagger.Lazy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListRecipesFragment : DaggerFragment(R.layout.fragment_list_recipes) {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelFactory>
    private val viewModel by viewModels<ListRecipesViewModel>(factoryProducer = { viewModelFactory.get() })
    private var binding: FragmentListRecipesBinding? = null
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
            listRecipes.adapter = adapter
        }
        viewModel.getRecipes.observe(viewLifecycleOwner) { recipes -> adapter.recipes = recipes }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}