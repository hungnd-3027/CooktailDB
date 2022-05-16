package com.example.cooktaildb.data.source.local.utils

interface OnRequestLocalCallback<T> {
    fun onSuccess(data: T)
    fun onFailed()
}