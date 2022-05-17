package com.example.cooktaildb.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.cooktaildb.NetworkUtils

class NetworkReceiver(private val networkCallback: OnNetworkChangeCallback): BroadcastReceiver() {
//    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "network change", Toast.LENGTH_SHORT).show()
        Log.e("TAG", "onReceive: network change", )
        networkCallback.onNetworkChange(NetworkUtils.isNetworkAvailable(p0))
    }
}
