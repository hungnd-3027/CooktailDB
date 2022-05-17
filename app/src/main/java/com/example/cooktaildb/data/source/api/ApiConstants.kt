package com.example.cooktaildb.data.source.api

object ApiConstants {
    const val SCHEME = "https"
    const val AUTHORITY = "www.thecocktaildb.com"
    const val PATH = "api/json/v1/1"

    const val SEARCH = "search.php"
    const val LOOKUP = "lookup.php"
    const val RANDOM = "random.php"
    const val FILTER = "filter.php"
    const val LIST = "list.php"
}

object ParameterConstants {
    const val CATEGORY_PARAM = "c"
    const val SEARCH_PARAM = "s"
    const val LOOKUP_PARAM = "i"
    const val ALCOHOLIC_PARAM = "a"
    const val GLASS_PARAM = "g"
    const val FIRST_LETTER_PARAM = "f"
}

object ValueConstants {
    const val LIST_VALUE = "list"
}
