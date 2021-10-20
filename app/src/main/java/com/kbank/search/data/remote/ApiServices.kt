package com.kbank.search.data.remote

import com.kbank.search.model.Media
import retrofit2.Response
import retrofit2.http.GET


interface ApiServices {


    @GET("/DummyFoodiumApi/api/posts/")
    suspend fun getPosts(): Response<List<Media>>
}