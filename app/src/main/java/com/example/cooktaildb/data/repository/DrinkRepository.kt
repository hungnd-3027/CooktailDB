package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.ISearchDrinkDataSource
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class DrinkRepository(
    private val iDrinkDataSource: IDrinkDataSource,
    private val iSearchDrinkDataSource: ISearchDrinkDataSource
) : IDrinkDataSource, ISearchDrinkDataSource {

    override fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.getDrinks(category, callback)
    }

    override fun searchDrink(strDrink: String, callback: OnRequestCallback<List<Drink>>) {
        iSearchDrinkDataSource.searchDrink(strDrink, callback)
    }

    companion object {
        private var instance: DrinkRepository? = null
        fun getInstance(
            iDrinkDataSource: IDrinkDataSource,
            iSearchDrinkDataSource: ISearchDrinkDataSource
        ) =
            instance ?: DrinkRepository(iDrinkDataSource, iSearchDrinkDataSource).also {
                instance = it
            }
    }
}
