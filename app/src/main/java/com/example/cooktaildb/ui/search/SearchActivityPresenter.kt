package com.example.cooktaildb.ui.search

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
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
}
