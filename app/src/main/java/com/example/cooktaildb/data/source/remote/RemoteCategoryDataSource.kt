package com.example.cooktaildb.data.source.remote

import com.example.cooktaildb.ApiDrinkConstant
import com.example.cooktaildb.ApiRespondConstant
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.source.ICategoryDataSource
import com.example.cooktaildb.data.source.api.ApiURL
import com.example.cooktaildb.data.source.utils.RemoteAsyncTask
import com.example.cooktaildb.data.source.utils.httpConnection
import org.json.JSONArray
import org.json.JSONObject

class RemoteCategoryDataSource : ICategoryDataSource {
    override fun getCategories(callback: OnRequestCallback<List<Category>>) {
        RemoteAsyncTask(callback) {
            getCategories()
        }.execute()
    }

    private fun getCategories(): List<Category> {
        val jsonObject = JSONObject(httpConnection(ApiURL.getCategoryList()))
        val jsonArray = jsonObject.optJSONArray(ApiRespondConstant.DRINKS)
        return getCategoriesFromJson(jsonArray)
    }

    private fun getCategoriesFromJson(jsonArray: JSONArray): List<Category> {
        val listCategory = mutableListOf<Category>()
        for (i in 0 until jsonArray.length()) {
            val jsonDrink = jsonArray.optJSONObject(i)
            val category = jsonDrink.optString(ApiDrinkConstant.CATEGORY)

            listCategory.add(Category(category))
        }
        return listCategory
    }

    companion object {
        private var instance: RemoteCategoryDataSource? = null
        fun getInstance() = instance ?: RemoteCategoryDataSource().also {
            instance = it
        }
    }
}
