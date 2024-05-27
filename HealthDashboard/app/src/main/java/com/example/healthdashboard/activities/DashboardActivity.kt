package com.example.healthdashboard.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthdashboard.Model.Vehicle
import com.example.healthdashboard.Networks.RetrofitClient
import com.example.healthdashboard.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val vehicleName: TextView = findViewById(R.id.vehicle_name_input)
        val batteryCondition: TextView = findViewById(R.id.battery_condition_input)
        val gasRemaining: TextView = findViewById(R.id.gas_remaining_input)
        val temperature: TextView = findViewById(R.id.temperature_input)
        val pressure: TextView = findViewById(R.id.pressure_input)

        val intent = intent
        val vehicleNo = intent.getStringExtra("vId")

        if (vehicleNo != null) {
            RetrofitClient.api.getVehicle(vehicleNo).enqueue(object : Callback<Vehicle>{

                override fun onResponse(p0: Call<Vehicle>, p1: Response<Vehicle>) {
                    val vehicle = p1.body()
                    if (p1.isSuccessful){
                        vehicle?.let {
                            vehicleName.text = it.vehicleName
                            batteryCondition.text = it.batteryCondition
                            gasRemaining.text = it.gasRemaining
                            temperature.text = it.temperature
                            pressure.text = it.pressure
                        }
                    } else {
                        Toast.makeText(this@DashboardActivity, "Network error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(p0: Call<Vehicle>, p1: Throwable) {
                    Toast.makeText(this@DashboardActivity, "Network error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}