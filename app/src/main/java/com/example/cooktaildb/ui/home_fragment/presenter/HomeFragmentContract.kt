package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink

interface HomeFragmentContract {
    interface View : BaseView {
        fun getListCategorySuccess(categories: List<Category>)
        fun failed()
        fun getDrinkBySelectedCategorySuccess(drinks: List<Drink>)
    }

    interface Presenter {
        fun getCategory()
        fun getDrinkBySelectedCategory(strCategory: String)
    }
}
