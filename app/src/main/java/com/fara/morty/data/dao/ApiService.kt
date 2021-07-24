package com.fara.morty.data.dao

import com.fara.morty.data.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): Response<ResponseApi>
}