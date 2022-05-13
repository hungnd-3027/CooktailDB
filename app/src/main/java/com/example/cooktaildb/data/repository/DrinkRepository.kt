package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class DrinkRepository(
    private val iDrinkDataSource: IDrinkDataSource
) : IDrinkDataSource {
    override fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.getDrinks(category, callback)
    }

    override fun searchDrink(strDrink: String, callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.searchDrink(strDrink, callback)
    }

    companion object {
        private var instance: DrinkRepository? = null
        fun getInstance(
            iDrinkDataSource: IDrinkDataSource
        ) =
            instance ?: DrinkRepository(iDrinkDataSource).also { instance = it }
    }
}
