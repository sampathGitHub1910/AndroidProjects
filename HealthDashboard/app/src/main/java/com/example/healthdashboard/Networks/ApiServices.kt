package com.example.healthdashboard.Networks

import com.example.healthdashboard.Model.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path

interface ApiServices {
    @GET("api/dashboard/{vehicleNo}")
    fun getVehicle(@Path("vehicleNo") vehicleNo: String): Call<Vehicle>

    @POST("api/dashboard")
    fun registerVehicle(@Body vehicle: Vehicle): Call<Vehicle>
}
