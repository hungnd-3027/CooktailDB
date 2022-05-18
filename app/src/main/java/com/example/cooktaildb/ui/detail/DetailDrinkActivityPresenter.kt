package com.example.cooktaildb.ui.detail

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
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

    override fun insertDrink(drink: Drink) {
        repository.insertDrink(drink, object : OnRequestLocalCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.insertDrinkSuccess()
                drink.idDrink?.let { isFavorite(it) }
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun deleteDrink(idDrink: String) {
        repository.deleteDrink(idDrink, object : OnRequestLocalCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.deleteDrinkSuccess()
                isFavorite(idDrink)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun isFavorite(idDrink: String) {
        repository.isFavorite(idDrink, object : OnRequestLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isFavorite(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }
}
