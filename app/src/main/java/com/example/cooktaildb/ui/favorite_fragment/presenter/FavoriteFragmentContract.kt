package com.example.cooktaildb.ui.favorite_fragment.presenter

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Drink

interface FavoriteFragmentContract {

    interface View : BaseView{
        fun getAllDrinkSuccess(data: List<Drink>)
        fun deleteDrinkSuccess()
    }

    interface Presenter {
        fun getAllDrink()
        fun deleteDrink(idDrink: String)
    }
}
