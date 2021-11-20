package com.w4eret1ckrtb1tch.recipesalderasoft.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import com.w4eret1ckrtb1tch.recipesalderasoft.databinding.FragmentListRecipesBinding

class ListRecipesFragment : Fragment(R.layout.fragment_list_recipes) {

    private var binding: FragmentListRecipesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}