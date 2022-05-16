package com.example.cooktaildb.ui.detail

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Drink

interface DetailDrinkActivityContract {
    interface View : BaseView {
        fun getDrinkByIDSuccess(drinks: List<Drink>)
        fun getRandomDrinkSuccess(drinks: List<Drink>)
    }

    interface Presenter {
        fun getDrinkByID(idDrink: String)
        fun getRandomDrink()
    }
}
