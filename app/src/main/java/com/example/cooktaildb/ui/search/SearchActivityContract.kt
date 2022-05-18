package com.example.cooktaildb.ui.search

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Drink

interface SearchActivityContract {

    interface View : BaseView {
        fun failed()
        fun getDrinkSuccess(drinks: List<Drink>)
        fun insertDrinkSuccess()
        fun isFavorite(result: Boolean, position: Int)
        fun deleteDrinkSuccess()
        fun getDrinkByIDSuccess(drinks: List<Drink>)
    }

    interface Presenter {
        fun searchDrink(strDrink: String)
        fun insertDrink(drink: Drink)
        fun isFavorite(idDrink: String, position: Int)
        fun deleteDrink(idDrink: String)
        fun getDrinkByID(idDrink: String)
    }
}
