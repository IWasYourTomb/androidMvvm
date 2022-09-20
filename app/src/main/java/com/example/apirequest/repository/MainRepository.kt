package com.example.apirequest.repository

import com.example.apirequest.api.RetrofitService
import com.example.apirequest.model.Movie
import com.example.apirequest.network.NetworkState

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() : NetworkState<List<Movie>> {
        val response = retrofitService.getAllMovies()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}