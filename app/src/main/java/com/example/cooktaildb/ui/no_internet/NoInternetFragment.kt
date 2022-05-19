package com.example.cooktaildb.ui.no_internet

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.example.cooktaildb.NetworkUtils
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.databinding.FragmentNoInternetBinding
import com.example.cooktaildb.receiver.NetworkReceiver
import com.example.cooktaildb.receiver.OnNetworkChangeCallback

class NoInternetFragment :
    BaseFragment<FragmentNoInternetBinding>(FragmentNoInternetBinding::inflate),
    View.OnClickListener,
    OnNetworkChangeCallback {

    private var lisener: OnItemClick? = null
    private val networkReceiver = NetworkReceiver(this)
    private var isPausing = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lisener = context as OnItemClick
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.registerReceiver(
            networkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        binding?.apply {
            buttonExit.setOnClickListener(this@NoInternetFragment)
            buttonConnectNetwork.setOnClickListener(this@NoInternetFragment)
        }
    }

    override fun onNetworkChange(isNetworkConnected: Boolean) {
        if (isNetworkConnected && !isPausing) {
            lisener?.onHandleNetworkEvent()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_exit -> lisener?.onClickExit()
            R.id.button_connect_network -> startActivity(Intent(Settings.ACTION_SETTINGS))
        }
    }

    override fun onResume() {
        super.onResume()
        if (NetworkUtils.isNetworkAvailable(context)) {
            lisener?.onHandleNetworkEvent()
        }
    }

    override fun onPause() {
        super.onPause()
        isPausing = true
    }

    interface OnItemClick {
        fun onClickExit()
        fun onHandleNetworkEvent()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NoInternetFragment()
    }
}
