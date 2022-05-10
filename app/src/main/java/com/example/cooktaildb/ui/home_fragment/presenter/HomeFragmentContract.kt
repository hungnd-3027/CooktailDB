package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.base.BaseView
import com.example.cooktaildb.data.model.Category

interface HomeFragmentContract {
    interface View : BaseView {
        fun getListCategorySuccess(categories: List<Category>)
        fun failed()
    }

    interface Presenter {
        fun getCategory()
    }
}
