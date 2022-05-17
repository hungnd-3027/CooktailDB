package com.example.cooktaildb.data.source.local.utils

import android.os.AsyncTask

class LocalAsyncTask<V, T>(
    private val callback: OnRequestLocalCallback<T>,
    private val handle: (V) -> T
) : AsyncTask<V, Unit, T?>() {

    override fun doInBackground(vararg params: V): T? =
        try {
            handle(params[0])
        } catch (e: Exception) {
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: callback::onFailed
    }
}
