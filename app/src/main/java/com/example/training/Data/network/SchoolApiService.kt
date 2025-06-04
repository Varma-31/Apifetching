package com.example.training.Data.network

import com.example.training.Data.model.School
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response


interface SchoolApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int
    ): Response<List<School>>
}



