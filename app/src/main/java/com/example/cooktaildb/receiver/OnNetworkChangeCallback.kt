package com.example.cooktaildb.receiver

interface OnNetworkChangeCallback {
    fun onNetworkChange(isNetworkConnected: Boolean)
}