package com.ryanb.jsonpractice.model.repository

import com.ryanb.jsonpractice.model.data.ContactInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/1")
    suspend fun getContactInfo(): ContactInfo

    @GET("users/{id}")
    suspend fun getContactInfoById(@Path("id") id: Int): ContactInfo
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
