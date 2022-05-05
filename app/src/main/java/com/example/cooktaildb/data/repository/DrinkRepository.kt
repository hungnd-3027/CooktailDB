package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.remote.OnRequestCallback
import com.example.cooktaildb.data.source.remote.RemoteDrinkDataSource

class DrinkRepository(private val iDrinkDataSource: IDrinkDataSource): IDrinkDataSource {
    override fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.getDrinks(category, callback)
    }
    companion object {
        private var instance : DrinkRepository? = null
        fun getInstance(iDrinkDataSource: IDrinkDataSource) =
            instance ?: DrinkRepository(iDrinkDataSource).also { instance = it }
    }
}
