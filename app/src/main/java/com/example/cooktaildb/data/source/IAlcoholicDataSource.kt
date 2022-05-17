package com.example.cooktaildb.data.source

import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.source.remote.OnRequestCallback

interface IAlcoholicDataSource {
    fun getAlcoholic(callback: OnRequestCallback<List<Alcoholic>>)
}
