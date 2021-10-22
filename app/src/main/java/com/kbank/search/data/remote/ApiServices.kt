package com.kbank.search.data.remote

import com.kbank.search.BuildConfig
import com.kbank.search.data.remote.model.SearchImageResponse
import com.kbank.search.data.remote.model.SearchVideoResponse
import com.kbank.search.model.Image
import com.kbank.search.model.Media
import com.kbank.search.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiServices {

    @Headers("Authorization:KakaoAK ${BuildConfig.KAKAO_API_KEY}")
    @GET("/v2/search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SearchVideoResponse>


    @Headers("Authorization:KakaoAK ${BuildConfig.KAKAO_API_KEY}")
    @GET("/v2/search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SearchImageResponse>

}