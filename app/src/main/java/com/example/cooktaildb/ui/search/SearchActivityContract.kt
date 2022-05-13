package com.example.cooktaildb.ui.search

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Drink

interface SearchActivityContract {

    interface View : BaseView {
        fun failed()
        fun getDrinkSuccess(drinks: List<Drink>)
    }

    interface Presenter {
        fun searchDrink(strDrink: String)
    }
}
