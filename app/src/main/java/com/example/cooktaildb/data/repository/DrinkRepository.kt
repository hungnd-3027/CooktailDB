package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class DrinkRepository(
    private val iDrinkDataSource: IDrinkDataSource,
    private val local: IDrinkDataSource.Local
) : IDrinkDataSource, IDrinkDataSource.Local {

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

    override fun insertDrink(drink: Drink, callback: OnRequestLocalCallback<Unit>) {
        local.insertDrink(drink, callback)
    }

    override fun deleteDrink(idDrink: String, callback: OnRequestLocalCallback<Unit>) {
        local.deleteDrink(idDrink, callback)
    }

    override fun getAllDrink(callback: OnRequestLocalCallback<List<Drink>>) {
        local.getAllDrink(callback)
    }

    override fun isFavorite(idDrink: String, callback: OnRequestLocalCallback<Boolean>) {
        local.isFavorite(idDrink, callback)
    }

    companion object {
        private var instance: DrinkRepository? = null
        fun getInstance(
            iDrinkDataSource: IDrinkDataSource,
            local: IDrinkDataSource.Local
        ) =
            instance ?: DrinkRepository(iDrinkDataSource, local).also { instance = it }
    }
}
