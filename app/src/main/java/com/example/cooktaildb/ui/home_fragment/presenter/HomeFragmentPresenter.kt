package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class HomeFragmentPresenter(
    private val repository: CategoryRepository,
    private val view: HomeFragmentContract.View
) : HomeFragmentContract.Presenter {

    override fun getCategory() {
        repository.getCategories(object : OnRequestCallback<List<Category>> {
            override fun onSuccess(data: List<Category>) {
                view.getListCategorySuccess(data)
            }

            override fun onFailed() {
                view.failed()
            }
        })
    }
}
