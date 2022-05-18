package com.example.cooktaildb.data.source.local.dao

import androidx.core.content.contentValuesOf
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.source.local.DatabaseHelper

class FavoriteDrinkDAOImpl(databaseHelper: DatabaseHelper) : FavoriteDrinkDAO {

    private val writeDB = databaseHelper.writableDatabase
    private val readDB = databaseHelper.readableDatabase

    override fun insertDrink(drink: Drink) {
        val strIngredients = StringBuilder()
        drink.ingredients.apply {
            forEachIndexed { index, s ->
                strIngredients.append(if (index < size - 1) String.format(appendPattern, s) else s)
            }
        }

        val strMeasures = StringBuilder()
        drink.measures.apply {
            forEachIndexed { index, s ->
                strMeasures.append(if (index < size - 1) String.format(appendPattern, s) else s)
            }
        }

        val contentValue = contentValuesOf(
            DatabaseHelper.ID to drink.idDrink,
            DatabaseHelper.NAME to drink.strDrink,
            DatabaseHelper.CATEGORY to drink.strCategory,
            DatabaseHelper.ALCOHOLIC to drink.strAlcoholic,
            DatabaseHelper.GLASS to drink.strGlass,
            DatabaseHelper.INSTRUCTION to drink.strInstructions,
            DatabaseHelper.THUMB to drink.strDrinkThumb,
            DatabaseHelper.INGREDIENTS to strIngredients.toString(),
            DatabaseHelper.MEASURES to strMeasures.toString(),
        )

        writeDB.insert(DatabaseHelper.TABLE_NAME, null, contentValue)
    }

    override fun deleteDrink(idDrink: String) {
        writeDB.delete(DatabaseHelper.TABLE_NAME, "${DatabaseHelper.ID} = ?", arrayOf(idDrink))
    }

    override fun getAllDrink(): List<Drink> {
        val drinks = mutableListOf<Drink>()
        val selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_NAME
        val cursor = readDB.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val drink = Drink()
                drink.idDrink = cursor.getString(0)
                drink.strDrink = cursor.getString(1)
                drink.strCategory = cursor.getString(2)
                drink.strAlcoholic = cursor.getString(3)
                drink.strGlass = cursor.getString(4)
                drink.strInstructions = cursor.getString(5)
                drink.strDrinkThumb = cursor.getString(6)
                cursor.getString(7).apply {
                    val ingredientList = this.split(";")
                    drink.ingredients = ingredientList.toList()
                }
                cursor.getString(8).apply {
                    val measureList = this.split(";")
                    drink.measures = measureList.toList()
                }
                drinks.add(drink)
            } while (cursor.moveToNext())
        }
        return drinks
    }

    override fun isFavorite(idDrink: String): Boolean {
        val query = "${DatabaseHelper.ID} = ?"
        val cursor = readDB.query(
            DatabaseHelper.TABLE_NAME,
            null,
            query,
            arrayOf(idDrink),
            null,
            null,
            null
        )
        return cursor.count > 0
    }

    companion object {
        const val appendPattern = "%s;"

        private var instance: FavoriteDrinkDAOImpl? = null

        fun getInstance(database: DatabaseHelper): FavoriteDrinkDAOImpl =
            instance ?: FavoriteDrinkDAOImpl(database).also { instance = it }
    }
}
