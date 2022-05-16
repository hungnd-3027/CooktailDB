package com.example.cooktaildb.data.model

data class Drink(
    var idDrink: String? = "",
    var strDrink: String? = "",
    var strVideo: String? = "",
    var strCategory: String? = "",
    var strAlcoholic: String = "",
    var strGlass: String? = "",
    var strInstructions: String? = "",
    var strDrinkThumb: String? = "",
    var ingredients: List<String?> = listOf(),
    var measures: List<String?> = listOf(),
    var strImageSource: String? = ""
)
