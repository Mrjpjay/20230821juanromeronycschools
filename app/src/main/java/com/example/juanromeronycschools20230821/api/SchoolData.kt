package com.example.juanromeronycschools20230821.api

import com.google.gson.annotations.SerializedName

data class SchoolData(
    val dbn: String = "",
    @SerializedName("school_name") val schoolName: String? ="",
    val neighborhood: String ="",
    val location: String ="",
    val website: String =""
)