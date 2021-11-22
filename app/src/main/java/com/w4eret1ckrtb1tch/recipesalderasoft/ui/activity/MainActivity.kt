package com.w4eret1ckrtb1tch.recipesalderasoft.ui.activity

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showDescription(description: String, view: View, dataLoad: (() -> Unit)?) {
        val snackBar = Snackbar.make(view, description, Snackbar.LENGTH_INDEFINITE)
        snackBar
            .setMaxInlineActionWidth(resources.getDimensionPixelSize(R.dimen.design_snackbar_action_inline_max_width))
            .setAction(R.string.retry) { dataLoad?.invoke(); snackBar.dismiss() }
            .show()
    }
}