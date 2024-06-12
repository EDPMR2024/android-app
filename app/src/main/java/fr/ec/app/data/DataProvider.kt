package fr.ec.app.data

import com.google.gson.Gson
import fr.ec.app.data.api.response.PostResponse
import fr.ec.app.data.api.response.PostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

object DataProvider {


    private val POST_API_URL =
        "https://raw.githubusercontent.com/EDPMR2024/android-app/main/data/posts/posts.json"

    private val gson = Gson()
    suspend fun getPosts(): List<PostResponse> = withContext(Dispatchers.Default) {
        val json = makeCall()
        val postsResponse = gson.fromJson(json, PostsResponse::class.java)
        postsResponse.posts
    }

    private suspend fun makeCall(): String? = withContext(Dispatchers.IO) {
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        try {
            urlConnection = URL(POST_API_URL).openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            reader = urlConnection.inputStream?.bufferedReader()
            reader?.readText()

        } finally {
            urlConnection?.disconnect()
            reader?.close()
        }
    }
}