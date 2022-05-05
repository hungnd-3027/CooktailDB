package com.example.cooktaildb.data.source.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

fun httpConnection(uri: String): String {
    val content = StringBuilder("")
    var httpConn: HttpURLConnection? = null
    try {
        val url = URL(uri)
        httpConn = url.openConnection() as HttpURLConnection
        val inputStreamReader = InputStreamReader(httpConn.inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        bufferedReader.forEachLine {
            content.append(it)
        }
    } catch (ex: MalformedURLException) {
        ex.printStackTrace()
    } catch (ex: IOException) {
        ex.printStackTrace()
    } finally {
        httpConn?.disconnect()
    }
    return content.toString()
}
