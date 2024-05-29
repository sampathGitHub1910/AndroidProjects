package com.example.project05.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project05.R
import com.example.project05.RegisterActivity
import com.example.project05.VehicleDetailActivity
import com.example.project05.models.Vehicle
import com.example.project05.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var searchButton: Button
    private lateinit var vehicleNoEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton = findViewById(R.id.searchButton)
        vehicleNoEditText = findViewById(R.id.vehicleNoEditText)

        searchButton.setOnClickListener {
            val vehicleNo = vehicleNoEditText.text.toString()
            searchVehicle(vehicleNo)
        }

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun searchVehicle(vehicleNo: String) {
        RetrofitClient.vehicleApi.getVehicleDetails(vehicleNo).enqueue(object : Callback<Vehicle> {

            override fun onResponse(p0: Call<Vehicle>, p1: Response<Vehicle>) {
                if (p1.isSuccessful) {
                    val vehicle = p1.body()
                    vehicle?.let {
                        val intent = Intent(this@MainActivity, VehicleDetailActivity::class.java)
                        intent.putExtra("vno", vehicle.vehicle_no)
                        intent.putExtra("vname", vehicle.vehicle_name)
                        intent.putExtra("bc", vehicle.battery_condition)
                        intent.putExtra("gr", vehicle.gas_remaining)
                        intent.putExtra("temp", vehicle.temperature)
                        intent.putExtra("p", vehicle.pressure)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Vehicle not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Vehicle>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
