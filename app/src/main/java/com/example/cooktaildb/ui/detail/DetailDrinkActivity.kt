package com.example.cooktaildb.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.cooktaildb.base.BaseActivity
import com.example.cooktaildb.databinding.ActivityDetailDrinkBinding

class DetailDrinkActivity : BaseActivity<ActivityDetailDrinkBinding>(ActivityDetailDrinkBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun getIntent(startFragment: Context) = Intent(startFragment, DetailDrinkActivity::class.java)
    }
}
