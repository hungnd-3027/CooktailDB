package com.example.cooktaildb.data.source.remote

import com.example.cooktaildb.ApiDrinkConstant
import com.example.cooktaildb.ApiRespondConstant
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.api.ApiURL
import com.example.cooktaildb.data.source.utils.RemoteAsyncTask
import com.example.cooktaildb.data.source.utils.httpConnection
import org.json.JSONArray
import org.json.JSONObject

class RemoteDrinkDataSource : IDrinkDataSource {

    override fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>) {
        val apiUrl = ApiURL.getDrinkList(category)
        RemoteAsyncTask(callback) {
            getData(apiUrl)
        }.execute()
    }

    override fun searchDrink(strDrink: String, callback: OnRequestCallback<List<Drink>>) {
        val apiUrl = ApiURL.searchDrink(strDrink)
        RemoteAsyncTask(callback) {
            getData(apiUrl)
        }.execute()
    }

    override fun getDrinkByID(idDrink: String, callback: OnRequestCallback<List<Drink>>) {
        val apiUrl = ApiURL.getDrinkByID(idDrink)
        RemoteAsyncTask(callback) {
            getData(apiUrl)
        }.execute()
    }

    override fun getRandomDrink(callback: OnRequestCallback<List<Drink>>) {
        val apiUrl = ApiURL.getRandomDrink()
        RemoteAsyncTask(callback) {
            getData(apiUrl)
        }.execute()
    }

    private fun getData(apiUrl: String): List<Drink> {
        val jsonObject = JSONObject(httpConnection(apiUrl))
        return try {
            val jsonArray = jsonObject.getJSONArray(ApiRespondConstant.DRINKS)
            getDrinkFromJson(jsonArray)
        } catch (e: Exception) {
            listOf()
        }
    }

    private fun getDrinkFromJson(jsonArray: JSONArray): List<Drink> {
        val listDrinks = mutableListOf<Drink>()
        for (i in 0 until jsonArray.length()) {
            val jsonDrink = jsonArray.optJSONObject(i)
            val id = jsonDrink.optString(ApiDrinkConstant.ID)
            val name = jsonDrink.optString(ApiDrinkConstant.NAME)
            val thumb = jsonDrink.optString(ApiDrinkConstant.THUMB)
            val video = jsonDrink.optString(ApiDrinkConstant.VIDEO)
            val alcoholic = jsonDrink.optString(ApiDrinkConstant.ALCOHOLIC)
            val glass = jsonDrink.optString(ApiDrinkConstant.GLASS)
            val category = jsonDrink.optString(ApiDrinkConstant.CATEGORY)
            val instruction = jsonDrink.optString(ApiDrinkConstant.INSTRUCTION)
            val ingredients = ArrayList<String>()
            val measures = ArrayList<String>()
            val imageSource = jsonDrink.optString(ApiDrinkConstant.IMAGE_SOURCE)
            ApiDrinkConstant.INGREDIENTS.forEach {
                ingredients.add(jsonDrink.optString(it))
            }
            ApiDrinkConstant.MEASURES.forEach {
                measures.add(jsonDrink.optString(it))
            }
            listDrinks.add(
                Drink(
                    id,
                    name,
                    video,
                    category,
                    alcoholic,
                    glass,
                    instruction,
                    thumb,
                    ingredients,
                    measures,
                    imageSource
                )
            )
        }
        return listDrinks
    }

    companion object {
        private var instance: RemoteDrinkDataSource? = null
        fun getInstance() = instance ?: RemoteDrinkDataSource().also {
            instance = it
        }
    }
}
