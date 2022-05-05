package com.example.cooktaildb.data.source.remote

import com.example.cooktaildb.ApiDrinkConstant
import com.example.cooktaildb.ApiRespondConstant
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.api.ApiConstants
import com.example.cooktaildb.data.source.api.ApiURL
import com.example.cooktaildb.data.source.utils.RemoteAsyncTask
import com.example.cooktaildb.data.source.utils.httpConnection
import org.json.JSONArray
import org.json.JSONObject

class RemoteDrinkDataSource: IDrinkDataSource {
    override fun getDrinks(category: String, callback: OnRequestCallback<List<Drink>>) {
        RemoteAsyncTask(callback){
            getDrinks(category)
        }.execute()
    }
    private fun getDrinks(category: String): List<Drink> {
        val jsonObject = JSONObject(httpConnection(ApiURL.getDrinkList(category)))
        val jsonArray = jsonObject.getJSONArray(ApiRespondConstant.DRINKS)
        return getDrinkFromJson(jsonArray)
    }

    private fun getDrinkFromJson(jsonArray: JSONArray): List<Drink> {
        val listDrinks = mutableListOf<Drink>()
        for (i in 0 until jsonArray.length()) {
            val jsonDrink = jsonArray.optJSONObject(i)
            val id = jsonDrink.optString(ApiDrinkConstant.ID)
            val name = jsonDrink.optString(ApiDrinkConstant.NAME)
            val thumb = jsonDrink.optString(ApiDrinkConstant.THUMB)

            listDrinks.add(Drink(idDrink = id, strDrink = name, strDrinkThumb = thumb))
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
