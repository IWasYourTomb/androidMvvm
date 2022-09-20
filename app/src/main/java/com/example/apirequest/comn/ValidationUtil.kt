package com.example.apirequest.comn

import com.example.apirequest.model.Movie

object ValidationUtil {
    fun validateMovie(movie: Movie): Boolean{
        if(movie.name.isEmpty() && movie.category.isNotEmpty()){
            return true
        }
        return false
    }
}