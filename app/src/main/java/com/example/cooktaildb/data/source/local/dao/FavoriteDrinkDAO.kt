package com.example.cooktaildb.data.source.local.dao

import com.example.cooktaildb.data.model.Drink

interface FavoriteDrinkDAO {
    fun insertDrink(drink: Drink)
    fun deleteDrink(idDrink: String)
    fun getAllDrink(): List<Drink>
    fun isFavorite(idDrink: String): Boolean
}
