package com.hotmovies.repository.tmdb

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MoviesTmdbManager {

    fun retrieveMovies(): String? {

        val url = URL(MoviesTmdbDbContract.POPULAR_MOVIES_BASE_URL)
        val httpClient = url.openConnection() as HttpURLConnection
        if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {

            try {

                val stream = BufferedInputStream(httpClient.inputStream)

                return readStream(inputStream = stream)

            } catch (e: Exception) {

                e.printStackTrace()

            } finally {

                httpClient.disconnect()
            }

        } else {

            println("ERROR ${httpClient.responseCode}")
        }

        return null
    }

    private fun readStream(inputStream: BufferedInputStream): String {

        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        bufferedReader.forEachLine {
            stringBuilder.append(it)
        }

        return stringBuilder.toString()
    }

}