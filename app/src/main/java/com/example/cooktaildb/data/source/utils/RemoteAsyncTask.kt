package com.example.cooktaildb.data.source.utils

import android.os.AsyncTask
import com.example.cooktaildb.data.source.remote.OnRequestCallback

class RemoteAsyncTask<T>(
    private val callback: OnRequestCallback<T>,
    private val handle: () -> T
) : AsyncTask<Unit, Unit, T?>(){

    override fun doInBackground(vararg p0: Unit?): T? {
        return handle()
    }

    override fun onPostExecute(result: T?) {
        super.onPostExecute(result)
        result?.let(callback::onSuccess) ?: callback.onFailed()
    }
}
