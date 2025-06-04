package com.example.training.Data.model

import com.google.gson.annotations.SerializedName
// School.kt

data class School(
    val dbn: String?,
    @SerializedName("school_name")
    val schoolName: String?
)
