package com.example.cooktaildb.data.source.api

import android.net.Uri

object ApiURL {

    fun getDrinkList(strCategory: String) = Uri.Builder()
        .scheme(ApiConstants.SCHEME)
        .authority(ApiConstants.AUTHORITY)
        .appendPath(ApiConstants.PATH)
        .appendPath(ApiConstants.FILTER)
        .appendQueryParameter(ParameterConstants.CATEGORY_PARAM, strCategory)
        .toString()

    fun getCategoryList() = Uri.Builder()
        .scheme(ApiConstants.SCHEME)
        .authority(ApiConstants.AUTHORITY)
        .appendPath(ApiConstants.PATH)
        .appendPath(ApiConstants.LIST)
        .appendQueryParameter(ParameterConstants.CATEGORY_PARAM, ValueConstants.LIST_VALUE)
        .toString()

    fun searchDrink(strDrink: String) = Uri.Builder()
        .scheme(ApiConstants.SCHEME)
        .authority(ApiConstants.AUTHORITY)
        .appendPath(ApiConstants.PATH)
        .appendPath(ApiConstants.SEARCH)
        .appendQueryParameter(ParameterConstants.SEARCH_PARAM, strDrink)
        .toString()
}
