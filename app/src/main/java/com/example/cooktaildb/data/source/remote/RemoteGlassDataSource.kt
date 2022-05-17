package com.example.cooktaildb.data.source.remote

import com.example.cooktaildb.ApiDrinkConstant
import com.example.cooktaildb.ApiRespondConstant
import com.example.cooktaildb.data.model.Glass
import com.example.cooktaildb.data.source.IGlassDataSource
import com.example.cooktaildb.data.source.api.ApiURL
import com.example.cooktaildb.data.source.utils.RemoteAsyncTask
import com.example.cooktaildb.data.source.utils.httpConnection
import org.json.JSONArray
import org.json.JSONObject

class RemoteGlassDataSource : IGlassDataSource {

    override fun getGlass(callback: OnRequestCallback<List<Glass>>) {
        RemoteAsyncTask(callback) {
            getGlasses()
        }.execute()
    }

    private fun getGlasses(): List<Glass> {
        val jsonObject = JSONObject(httpConnection(ApiURL.getGlassList()))
        val jsonArray = jsonObject.optJSONArray(ApiRespondConstant.DRINKS)
        return getGlassFromJson(jsonArray)
    }

    private fun getGlassFromJson(jsonArray: JSONArray): List<Glass> {
        val listGlass = mutableListOf<Glass>()
        for (i in 0 until jsonArray.length()) {
            val jsonDrink = jsonArray.optJSONObject(i)
            val glass = jsonDrink.optString(ApiDrinkConstant.GLASS)

            listGlass.add(Glass(glass))
        }
        return listGlass
    }

    companion object {
        private var instance: RemoteGlassDataSource? = null
        fun getInstance() = instance ?: RemoteGlassDataSource().also {
            instance = it
        }
    }
}
