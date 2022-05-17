package com.example.cooktaildb.data.source.local

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.IDrinkDataSource
import com.example.cooktaildb.data.source.local.dao.FavoriteDrinkDAO
import com.example.cooktaildb.data.source.local.utils.LocalAsyncTask
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback

class LocalDrinkDataSource (private val dao: FavoriteDrinkDAO) : IDrinkDataSource.Local {

    override fun insertDrink(drink: Drink, callback: OnRequestLocalCallback<Unit>) {
        LocalAsyncTask<Drink, Unit>(callback){
            dao.insertDrink(drink)
        }.execute(drink)
    }

    override fun deleteDrink(idDrink: String, callback: OnRequestLocalCallback<Unit>) {
        LocalAsyncTask<String, Unit>(callback){
            dao.deleteDrink(idDrink)
        }.execute(idDrink)
    }

    override fun getAllDrink(callback: OnRequestLocalCallback<List<Drink>>) {
        LocalAsyncTask<Unit, List<Drink>>(callback){
            dao.getAllDrink()
        }.execute(Unit)
    }

    override fun isFavorite(idDrink: String, callback: OnRequestLocalCallback<Boolean>) {
        LocalAsyncTask<String, Boolean>(callback) {
            dao.isFavorite(idDrink)
        }.execute(idDrink)
    }

    companion object {
        private var instance: LocalDrinkDataSource? = null

        fun getInstance(favoriteDao: FavoriteDrinkDAO) =
            instance ?: LocalDrinkDataSource(favoriteDao).also { instance = it }
    }
}
