package com.example.cooktaildb.data.source

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback

interface IDrinkDataSource {

    interface Remote {
        fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>)

        fun searchDrink(strDrink: String, callback: OnRequestCallback<List<Drink>>)

        fun getDrinkByID(idDrink: String, callback: OnRequestCallback<List<Drink>>)

        fun getRandomDrink(callback: OnRequestCallback<List<Drink>>)

        fun getDrinkByAlcoholic(strAlcoholic: String, callback: OnRequestCallback<List<Drink>>)

        fun getDrinkByGlass(strGlass: String, callback: OnRequestCallback<List<Drink>>)

        fun getDrinkByFirstLetter(strFirstLetter: String, callback: OnRequestCallback<List<Drink>>)
    }

    interface Local {
        fun insertDrink(drink: Drink, callback: OnRequestLocalCallback<Unit>)
        fun deleteDrink(idDrink: String, callback: OnRequestLocalCallback<Unit>)
        fun getAllDrink(callback: OnRequestLocalCallback<List<Drink>>)
        fun isFavorite(idDrink: String, callback: OnRequestLocalCallback<Boolean>)
    }
}
