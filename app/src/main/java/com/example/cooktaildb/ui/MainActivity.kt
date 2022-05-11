package com.example.cooktaildb.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseActivity
import com.example.cooktaildb.databinding.ActivityMainBinding
import com.example.cooktaildb.ui.favorite_fragment.FavoriteFragment
import com.example.cooktaildb.ui.filter_fragment.FilterFragment
import com.example.cooktaildb.ui.home_fragment.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    NavigationBarView.OnItemSelectedListener {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.bottomNavigationView?.setOnItemSelectedListener(this)

        addFragment(HomeFragment.newInstance(), R.id.frame_navigation, null)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_home -> {
                replaceFragment(HomeFragment.newInstance(), R.id.frame_navigation, null)
                true
            }
            R.id.menu_filter -> {
                replaceFragment(FilterFragment.newInstance(), R.id.frame_navigation, null)
                true
            }
            R.id.menu_favorite -> {
                replaceFragment(FavoriteFragment.newInstance(), R.id.frame_navigation, null)
                true
            }
            else -> false
        }
    }

    companion object {
        fun getIntent(startActivity: Activity) = Intent(startActivity, MainActivity::class.java)
    }
}
