package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Glass
import com.example.cooktaildb.data.source.IGlassDataSource
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class GlassRepository(private val iGlassDataSource: IGlassDataSource) : IGlassDataSource {

    override fun getGlass(callback: OnRequestCallback<List<Glass>>) {
        iGlassDataSource.getGlass(callback)
    }

    companion object {
        private var instance: GlassRepository? = null
        fun getInstance(iGlassDataSource: IGlassDataSource) =
            instance ?: GlassRepository(iGlassDataSource).also { instance = it }
    }
}
