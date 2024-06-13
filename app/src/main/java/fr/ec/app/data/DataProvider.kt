package fr.ec.app.data

import com.google.gson.Gson
import fr.ec.app.data.api.response.PostResponse
import fr.ec.app.data.api.response.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    private val BASE_URL =
        "https://raw.githubusercontent.com/EDPMR2024/android-app/main/data/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postService = retrofit.create(PostService::class.java)
    suspend fun getPosts(): List<PostResponse> = postService.getPosts().posts

}