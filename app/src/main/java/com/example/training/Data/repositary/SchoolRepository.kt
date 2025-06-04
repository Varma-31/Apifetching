package com.example.training.Data.repositary

import android.util.Log
import com.example.training.Data.model.School
import com.example.training.Data.network.RetrofitInstance



class SchoolRepository {
    suspend fun fetchSchools(limit: Int, offset: Int): List<School> {
        val response = RetrofitInstance.api.getSchools(limit, offset)

        if (response.isSuccessful) {
            val data = response.body()
            Log.d("SchoolRepository", "API SUCCESS: ${data?.size} schools fetched")
            return data ?: emptyList()
        } else {
            Log.e("SchoolRepository", "API FAILURE: ${response.code()} ${response.message()}")
            return emptyList()
        }
    }
}
