package com.example.juanromeronycschools20230821.api

import com.example.juanromeronycschools20230821.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolsService {

    private lateinit var retrofit: Retrofit

    val instance: SchoolsEndPoint by lazy {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SchoolsEndPoint::class.java)
    }
}