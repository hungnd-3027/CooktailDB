package com.example.cooktaildb.data.source

import com.example.cooktaildb.data.model.Glass
import com.example.cooktaildb.data.source.remote.OnRequestCallback

interface IGlassDataSource {
    fun getGlass(callback: OnRequestCallback<List<Glass>>)
}
