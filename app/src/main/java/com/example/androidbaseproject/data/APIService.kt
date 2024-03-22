package com.example.androidbaseproject.data

import com.example.androidbaseproject.data.response.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search/trending")
    suspend fun getTrending(): Response<TrendingResponse>
}