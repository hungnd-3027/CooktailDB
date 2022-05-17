package com.example.cooktaildb.ui

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.cooktaildb.NetworkUtils
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseActivity
import com.example.cooktaildb.databinding.ActivityMainBinding
import com.example.cooktaildb.receiver.NetworkReceiver
import com.example.cooktaildb.receiver.OnNetworkChangeCallback
import com.example.cooktaildb.ui.favorite_fragment.FavoriteFragment
import com.example.cooktaildb.ui.filter_fragment.FilterFragment
import com.example.cooktaildb.ui.home_fragment.HomeFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    NavigationBarView.OnItemSelectedListener, OnNetworkChangeCallback {

    private val networkReceiver = NetworkReceiver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.bottomNavigationView?.setOnItemSelectedListener(this)
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun initData() {
        addFragment(
            if (NetworkUtils.isNetworkAvailable(this)) HomeFragment.newInstance() else FavoriteFragment.newInstance(),
            R.id.frame_navigation,
            FragmentConstant.HOME_FRAGMENT_TAG
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_home -> {
                replaceFragment(HomeFragment.newInstance(), R.id.frame_navigation, FragmentConstant.HOME_FRAGMENT_TAG)
                true
            }
            R.id.menu_filter -> {
                replaceFragment(
                    FilterFragment.newInstance(),
                    R.id.frame_navigation,
                    FragmentConstant.FILTER_FRAGMENT_TAG
                )
                true
            }
            R.id.menu_favorite -> {
                replaceFragment(
                    FavoriteFragment.newInstance(),
                    R.id.frame_navigation,
                    FragmentConstant.FAVORITE_FRAGMENT_TAG
                )
                true
            }
            else -> false
        }
    }

    override fun onNetworkChange(isNetworkConnected: Boolean) {
        if (!isNetworkConnected) {
            binding?.bottomNavigationView?.visibility = View.GONE
            replaceFragment(NoInternetFragment.newInstance(), R.id.frame_navigation, "null")
        } else {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    companion object {
        fun getIntent(startActivity: Activity) = Intent(startActivity, MainActivity::class.java)
    }
}
