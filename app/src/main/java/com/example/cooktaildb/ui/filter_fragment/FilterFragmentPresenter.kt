package com.example.cooktaildb.ui.filter_fragment

import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.model.Glass
import com.example.cooktaildb.data.repository.AlcoholicRepository
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.repository.GlassRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class FilterFragmentPresenter(
    private val categoryRepository: CategoryRepository,
    private val alcoholicRepository: AlcoholicRepository,
    private val glassRepository: GlassRepository,
    private val drinkRepository: DrinkRepository,
    val view: FilterFragmentContract.View
) : FilterFragmentContract.Presenter {
    override fun getCategory() {
        view.showProgressBar()
        categoryRepository.getCategories(object : OnRequestCallback<List<Category>> {
            override fun onSuccess(data: List<Category>) {
                view.getCategorySuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.hideProgressBar()
                view.showError()
            }
        })
    }

    override fun getAlcoholic() {
        view.showProgressBar()
        alcoholicRepository.getAlcoholic(object : OnRequestCallback<List<Alcoholic>> {
            override fun onSuccess(data: List<Alcoholic>) {
                view.getAlcoholicSuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.hideProgressBar()
                view.showError()
            }
        })
    }

    override fun getGlass() {
        view.showProgressBar()
        glassRepository.getGlass(object : OnRequestCallback<List<Glass>> {
            override fun onSuccess(data: List<Glass>) {
                view.getGlassSuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.hideProgressBar()
                view.showError()
            }
        })
    }

    override fun getDrinkByCategory(strCategory: String) {
        drinkRepository.getDrinks(strCategory, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByCategorySuccess(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getDrinkByAlcoholic(strAlcoholic: String) {
        drinkRepository.getDrinkByAlcoholic(strAlcoholic, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByCategorySuccess(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getDrinkByGlass(strGlass: String) {
        drinkRepository.getDrinkByGlass(strGlass, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByCategorySuccess(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getDrinkByFirstLetter(letter: String) {
        drinkRepository.getDrinkByFirstLetter(letter, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByCategorySuccess(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun insertDrink(drink: Drink) {
        drinkRepository.insertDrink(drink, object : OnRequestLocalCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.insertDrinkSuccess()
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun isFavorite(idDrink: String, position: Int) {
        drinkRepository.isFavorite(idDrink, object : OnRequestLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isFavorite(data, position)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun deleteDrink(idDrink: String) {
        drinkRepository.deleteDrink(idDrink, object : OnRequestLocalCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.deleteDrinkSuccess()
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getDrinkByID(idDrink: String) {
        drinkRepository.getDrinkByID(idDrink, object : OnRequestCallback<List<Drink>>{
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByIDSuccess(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }
}
