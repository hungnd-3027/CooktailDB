package com.example.cooktaildb.data.repository

import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.source.ICategoryDataSource
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class CategoryRepository( private val iCategoryDataSource: ICategoryDataSource): ICategoryDataSource {
    override fun getCategories(callback: OnRequestCallback<List<Category>>) {
        iCategoryDataSource.getCategories(callback)
    }

    companion object {
        private var instance: CategoryRepository? = null
        fun getInstance(iCategoryDataSource: ICategoryDataSource) =
            instance ?: CategoryRepository(iCategoryDataSource).also { instance = it }
    }
}
