package fr.ec.app.data.api.response

import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts/posts.json")
    suspend fun getPosts(): PostsResponse
}