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

    override fun getDrinkByID(idDrink: String, callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.getDrinkByID(idDrink, callback)
    }

    override fun getRandomDrink(callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.getRandomDrink(callback)
    }

    override fun getDrinkByAlcoholic(
        strAlcoholic: String,
        callback: OnRequestCallback<List<Drink>>
    ) {
        iDrinkDataSource.getDrinkByAlcoholic(strAlcoholic, callback)
    }

    override fun getDrinkByGlass(strGlass: String, callback: OnRequestCallback<List<Drink>>) {
        iDrinkDataSource.getDrinkByGlass(strGlass, callback)
    }

    override fun getDrinkByFirstLetter(
        strFirstLetter: String,
        callback: OnRequestCallback<List<Drink>>
    ) {
        iDrinkDataSource.getDrinkByFirstLetter(strFirstLetter, callback)
    }

    companion object {
        private var instance: DrinkRepository? = null
        fun getInstance(
            iDrinkDataSource: IDrinkDataSource
        ) =
            instance ?: DrinkRepository(iDrinkDataSource).also { instance = it }
    }
}
