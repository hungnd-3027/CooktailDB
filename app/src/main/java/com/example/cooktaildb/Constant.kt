package com.example.cooktaildb

object ApiRespondConstant {
    const val DRINKS = "drinks"
}

object ApiDrinkConstant {
    const val ID = "idDrink"
    const val NAME = "strDrink"
    const val VIDEO = "strVideo"
    const val CATEGORY = "strCategory"
    const val ALCOHOLIC = "strAlcoholic"
    const val GLASS = "strGlass"
    const val INSTRUCTION = "strInstructions"
    const val THUMB = "strDrinkThumb"
    val INGREDIENTS = listOf("strIngredient1" , "strIngredient2" , "strIngredient3" , "strIngredient4" ,
        "strIngredient5" , "strIngredient6" , "strIngredient7" , "strIngredient8" , "strIngredient9" ,
        "strIngredient10" , "strIngredient11" , "strIngredient12" , "strIngredient13" , "strIngredient14" ,
        "strIngredient15")
    val MEASURES = listOf("strMeasure1" , "strMeasure2" , "strMeasure3" , "strMeasure4" ,
        "strMeasure5" , "strMeasure6" , "strMeasure7" , "strMeasure8" , "strMeasure9" ,
        "strMeasure" , "strMeasure11" , "strMeasure12" , "strMeasure13" , "strMeasure14" ,
        "strMeasure15")
    const val IMAGE_SOURCE = "strImageSource"
}

object Constant {
    const val NULL = "null"
    const val BUNDLE_ID_DRINK = "BUNDLE_ID_DRINK"
}

object FilterType {
    const val CATEGORY_FILTER = 1
    const val ALCOHOLIC_FILTER = 2
    const val GLASS_FILTER = 3
}
