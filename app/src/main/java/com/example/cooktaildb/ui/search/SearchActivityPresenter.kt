package com.example.cooktaildb.ui.search

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class SearchActivityPresenter(
    private val repository: DrinkRepository,
    private val view: SearchActivityContract.View
) : SearchActivityContract.Presenter {

    override fun searchDrink(strDrink: String) {
        view.showProgressBar()
        repository.searchDrink(strDrink, object : OnRequestCallback<List<Drink>> {
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkSuccess(data)
                view.hideProgressBar()
            }

            override fun onFailed() {
                view.failed()
                view.hideProgressBar()
            }
        })
    }

    override fun insertDrink(drink: Drink) {
        repository.insertDrink(drink, object : OnRequestLocalCallback<Unit> {
            override fun onSuccess(data: Unit) {
                view.insertDrinkSuccess()
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun isFavorite(idDrink: String, position: Int) {
        repository.isFavorite(idDrink, object : OnRequestLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isFavorite(data, position)
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
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }

    override fun getDrinkByID(idDrink: String) {
        repository.getDrinkByID(idDrink, object : OnRequestCallback<List<Drink>>{
            override fun onSuccess(data: List<Drink>) {
                view.getDrinkByIDSuccess(data)
            }

            override fun onFailed() {
                view.showError()
            }
        })
    }
}
