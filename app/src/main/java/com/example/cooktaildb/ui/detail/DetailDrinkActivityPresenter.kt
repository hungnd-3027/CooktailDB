package com.example.cooktaildb.ui.detail

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class DetailDrinkActivityPresenter(
    private val repository: DrinkRepository,
    private val view: DetailDrinkActivityContract.View
) : DetailDrinkActivityContract.Presenter {

    override fun getDrinkByID(idDrink: String) {
        view.showProgressBar()
        repository.getDrinkByID(idDrink, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByIDSuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.showError()
                view.hideProgressBar()
            }
        })
    }

    override fun getRandomDrink() {
        view.showProgressBar()
        repository.getRandomDrink(object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getRandomDrinkSuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.showError()
                view.hideProgressBar()
            }
        })
    }
}
