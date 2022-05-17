package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class DrinkRepository(
    private val remote: IDrinkDataSource.Remote,
    private val local: IDrinkDataSource.Local
) : IDrinkDataSource.Remote, IDrinkDataSource.Local {

    override fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>) {
        remote.getDrinks(category, callback)
    }

    override fun searchDrink(strDrink: String, callback: OnRequestCallback<List<Drink>>) {
        remote.searchDrink(strDrink, callback)
    }

    override fun getDrinkByID(idDrink: String, callback: OnRequestCallback<List<Drink>>) {
        remote.getDrinkByID(idDrink, callback)
    }

    override fun getRandomDrink(callback: OnRequestCallback<List<Drink>>) {
        remote.getRandomDrink(callback)
    }

    override fun getDrinkByAlcoholic(
        strAlcoholic: String,
        callback: OnRequestCallback<List<Drink>>
    ) {
        remote.getDrinkByAlcoholic(strAlcoholic, callback)
    }

    override fun getDrinkByGlass(strGlass: String, callback: OnRequestCallback<List<Drink>>) {
        remote.getDrinkByGlass(strGlass, callback)
    }

    override fun getDrinkByFirstLetter(
        strFirstLetter: String,
        callback: OnRequestCallback<List<Drink>>
    ) {
        remote.getDrinkByFirstLetter(strFirstLetter, callback)
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
            remote: IDrinkDataSource.Remote,
            local: IDrinkDataSource.Local
        ) =
            instance ?: DrinkRepository(remote, local).also { instance = it }
    }
}
