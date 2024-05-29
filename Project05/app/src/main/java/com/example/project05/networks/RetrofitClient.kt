package com.example.project05.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://apis-l1j6.onrender.com"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val vehicleApi: VehicleApi by lazy {
        retrofit.create(VehicleApi::class.java)
    }
}
