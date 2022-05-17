package com.example.cooktaildb.ui.filter_fragment

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.model.Glass

interface FilterFragmentContract {
    interface View : BaseView {
        fun getCategorySuccess(categories: List<Category>)
        fun getAlcoholicSuccess(alcoholicList: List<Alcoholic>)
        fun getGlassSuccess(glasses: List<Glass>)
        fun getDrinkByCategorySuccess(drinks : List<Drink>)
        fun getDrinkByAlcoholicSuccess(drinks : List<Drink>)
        fun getDrinkByGlassSuccess(drinks : List<Drink>)
        fun getDrinkByFirstLetterSuccess(drinks : List<Drink>)
        fun insertDrinkSuccess()
        fun getDrinkByIDSuccess(drinks: List<Drink>)
        fun isFavorite(result: Boolean, position: Int)
        fun deleteDrinkSuccess()
    }

    interface Presenter {
        fun getCategory()
        fun getAlcoholic()
        fun getGlass()
        fun getDrinkByCategory(strCategory: String)
        fun getDrinkByAlcoholic(strAlcoholic: String)
        fun getDrinkByGlass(strGlass: String)
        fun getDrinkByFirstLetter(letter: String)
        fun insertDrink(drink: Drink)
        fun isFavorite(idDrink: String, position: Int)
        fun getDrinkByID(idDrink: String)
        fun deleteDrink(idDrink: String)
    }
}
