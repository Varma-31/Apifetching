package com.example.training.Data.repositary

import com.example.training.Data.network.RetrofitInstance

class SchoolRepository {
    suspend fun getSchools() = RetrofitInstance.api.getSchools()
}