package com.example.cooktaildb.data.source.remote

import com.example.cooktaildb.ApiDrinkConstant
import com.example.cooktaildb.ApiRespondConstant
import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.source.IAlcoholicDataSource
import com.example.cooktaildb.data.source.api.ApiURL
import com.example.cooktaildb.data.source.utils.RemoteAsyncTask
import com.example.cooktaildb.data.source.utils.httpConnection
import org.json.JSONArray
import org.json.JSONObject

class RemoteAlcoholicDataSource : IAlcoholicDataSource {

    override fun getAlcoholic(callback: OnRequestCallback<List<Alcoholic>>) {
        RemoteAsyncTask(callback) {
            getAlcoholicList()
        }.execute()
    }

    private fun getAlcoholicList(): List<Alcoholic> {
        val jsonObject = JSONObject(httpConnection(ApiURL.getAlcoholicList()))
        val jsonArray = jsonObject.optJSONArray(ApiRespondConstant.DRINKS)
        return getAlcoholicFromJson(jsonArray)
    }

    private fun getAlcoholicFromJson(jsonArray: JSONArray): List<Alcoholic> {
        val listAlcoholic = mutableListOf<Alcoholic>()
        for (i in 0 until jsonArray.length()) {
            val jsonDrink = jsonArray.optJSONObject(i)
            val alcoholic = jsonDrink.optString(ApiDrinkConstant.ALCOHOLIC)

            listAlcoholic.add(Alcoholic(alcoholic))
        }
        return listAlcoholic
    }

    companion object {
        private var instance: RemoteAlcoholicDataSource? = null
        fun getInstance() = instance ?: RemoteAlcoholicDataSource().also {
            instance = it
        }
    }
}
