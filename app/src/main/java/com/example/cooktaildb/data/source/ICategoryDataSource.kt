package com.example.cooktaildb.data.source

import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.source.remote.OnRequestCallback

interface ICategoryDataSource {
    fun getCategories(callback: OnRequestCallback<List<Category>>)
}
