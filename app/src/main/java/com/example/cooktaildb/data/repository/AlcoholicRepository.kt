package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.source.IAlcoholicDataSource
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class AlcoholicRepository(private val iAlcoholicDataSource: IAlcoholicDataSource) :
    IAlcoholicDataSource {

    override fun getAlcoholic(callback: OnRequestCallback<List<Alcoholic>>) {
        iAlcoholicDataSource.getAlcoholic(callback)
    }

    companion object {
        private var instance: AlcoholicRepository? = null
        fun getInstance(iAlcoholicDataSource: IAlcoholicDataSource) =
            instance ?: AlcoholicRepository(iAlcoholicDataSource).also { instance = it }
    }
}
