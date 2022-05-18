package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink

interface HomeFragmentContract {
    interface View : BaseView {
        fun getListCategorySuccess(categories: List<Category>)
        fun failed()
        fun getDrinkBySelectedCategorySuccess(drinks: List<Drink>)
        fun insertDrinkSuccess()
        fun getDetailDrinkSuccess(drinks: List<Drink>)
        fun checkFavoriteSuccess(result: Boolean, position: Int)
        fun deleteDrinkSuccess()
    }

    interface Presenter {
        fun getCategory()
        fun getDrinkBySelectedCategory(strCategory: String)
        fun insertDrink(drink: Drink)
        fun isFavorite(idDrink: String, position: Int)
        fun getDetailDrink(idDrink: String)
        fun deleteDrink(idDrink: String)
    }
}
