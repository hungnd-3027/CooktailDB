package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class HomeFragmentPresenter(
    private val repository: CategoryRepository,
    private val drinkRepository: DrinkRepository,
    private val view: HomeFragmentContract.View
) : HomeFragmentContract.Presenter {

    override fun getCategory() {
        view.showProgressBar()
        repository.getCategories(object : OnRequestCallback<List<Category>> {
            override fun onSuccess(data: List<Category>) {
                view.getListCategorySuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.failed()
                view.hideProgressBar()
            }
        })
    }

    override fun getDrinkBySelectedCategory(strCategory: String) {
        drinkRepository.getDrinks(strCategory, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkBySelectedCategorySuccess(data)
            }

            override fun onFailed() {
                view.failed()
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
                view.checkFavoriteSuccess(data, position)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getDetailDrink(idDrink: String) {
        drinkRepository.getDrinkByID(idDrink, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDetailDrinkSuccess(data)
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
}
