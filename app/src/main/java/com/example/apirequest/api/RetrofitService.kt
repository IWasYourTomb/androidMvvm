package com.example.apirequest.api

import com.example.apirequest.model.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val client = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = client.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}