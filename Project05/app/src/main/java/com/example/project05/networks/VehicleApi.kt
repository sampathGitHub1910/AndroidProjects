package com.example.project05.networks

import com.example.project05.models.Vehicle
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VehicleApi {
    @GET("/api/dashboard/{vehicleNo}")
    fun getVehicleDetails(@Path("vehicleNo") vehicleNo: String): Call<Vehicle>

    @POST("/api/dashboard")
    fun registerVehicle(@Body vehicle: Vehicle): Call<Void>
}