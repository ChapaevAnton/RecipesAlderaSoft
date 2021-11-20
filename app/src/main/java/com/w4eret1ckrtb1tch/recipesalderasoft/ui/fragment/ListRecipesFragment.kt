package com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.FragmentListRecipesBinding
import com.w4eret1ckrtb1tch.recipesalderasoft.presentation.viewmodel.ListRecipesViewModel
import com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter.RecipesAdapter

class ListRecipesFragment : Fragment(R.layout.fragment_list_recipes) {

    private val viewModel by viewModels<ListRecipesViewModel>()
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