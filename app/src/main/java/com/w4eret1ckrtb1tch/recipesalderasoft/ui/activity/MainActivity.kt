package com.w4eret1ckrtb1tch.recipesalderasoft.ui.activity

import android.os.Bundle
import com.w4eret1ckrtb1tch.recipesalderasoft.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}