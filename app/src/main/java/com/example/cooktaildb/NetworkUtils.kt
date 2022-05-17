package com.example.cooktaildb

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService


object NetworkUtils {

//    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable (context: Context?): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val wifiNetwork = connMgr?.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileNetwork = connMgr?.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val isWifiConnected = wifiNetwork?.isConnected
        val isMobileConnected = mobileNetwork?.isConnected

//        val connectivityManager =
//            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                    return true
//                }
//            }
//        }
//        return false
        return isWifiConnected == true || isMobileConnected == true
    }
}