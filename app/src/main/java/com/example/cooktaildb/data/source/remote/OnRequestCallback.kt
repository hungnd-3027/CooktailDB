package com.example.cooktaildb.data.source.remote

interface OnRequestCallback<T> {
    fun onSuccess(data: T)
    fun onFailed()
}
