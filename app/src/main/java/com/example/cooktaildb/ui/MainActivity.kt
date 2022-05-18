package com.example.cooktaildb.ui

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
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
import com.example.cooktaildb.ui.no_internet.NoInternetFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    NavigationBarView.OnItemSelectedListener, OnNetworkChangeCallback,
    NoInternetFragment.OnItemClick {

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
                replaceAtRoot(
                    HomeFragment.newInstance(),
                    R.id.frame_navigation
                )
                true
            }
            R.id.menu_filter -> {
                replaceAtRoot(
                    FilterFragment.newInstance(),
                    R.id.frame_navigation
                )
                true
            }
            R.id.menu_favorite -> {
                replaceAtRoot(
                    FavoriteFragment.newInstance(),
                    R.id.frame_navigation
                )
                true
            }
            else -> false
        }
    }

    override fun onNetworkChange(isNetworkConnected: Boolean) {
        if (!isNetworkConnected) {
            binding?.bottomNavigationView?.visibility = View.GONE
            replaceFragment(NoInternetFragment.newInstance(), R.id.frame_navigation, null)
        }
    }

    override fun onClickExit() {
        moveTaskToBack(true)
        finish()
    }

    override fun onHandleNetworkEvent() {
        supportFragmentManager.apply {
            if (backStackEntryCount >= 2) popBackStack()
            else Intent(this@MainActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
        binding?.bottomNavigationView?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    companion object {
        fun getIntent(startActivity: Activity) = Intent(startActivity, MainActivity::class.java)
    }
}
