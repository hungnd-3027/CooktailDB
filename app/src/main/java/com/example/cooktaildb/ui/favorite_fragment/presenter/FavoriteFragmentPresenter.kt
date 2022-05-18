package com.example.cooktaildb.ui.favorite_fragment.presenter

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback

class FavoriteFragmentPresenter(
    private val repository: DrinkRepository,
    private val view: FavoriteFragmentContract.View
) : FavoriteFragmentContract.Presenter {

    override fun getAllDrink() {
        view.showProgressBar()
        repository.getAllDrink(object : OnRequestLocalCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getAllDrinkSuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.showError()
                view.hideProgressBar()
            }
        })
    }

    override fun deleteDrink(idDrink: String) {
        view.showProgressBar()
        repository.deleteDrink(idDrink, object : OnRequestLocalCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.deleteDrinkSuccess()
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.showError()
                view.hideProgressBar()
            }
        })
    }
}
