package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
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
}
