package com.example.healthdashboard.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthdashboard.Adapters.DashboardAdapter
import com.example.healthdashboard.Model.Vehicle
import com.example.healthdashboard.Networks.RetrofitClient
import com.example.healthdashboard.R
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: DashboardAdapter
    private lateinit var vehicleList: MutableList<Vehicle>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vehicleList = mutableListOf()
        adapter = DashboardAdapter(vehicleList)


        val searchButton: Button = findViewById(R.id.submit_button)
        val registerButton: Button = findViewById(R.id.register_button)
        val vehicleNoInput: EditText = findViewById(R.id.vehicle_number_input)

        searchButton.setOnClickListener {
            val vehicleNo = vehicleNoInput.text.toString()
            if (vehicleNo.isNotEmpty()) {
                searchVehicle(vehicleNo)
            } else {
                Toast.makeText(this, "Please enter a vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterVehicalActitity::class.java)
            startActivity(intent)
        }
    }

    private fun searchVehicle(vehicleNo: String) {
        RetrofitClient.api.getVehicle(vehicleNo).enqueue(object : Callback<Vehicle> {

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Vehicle>, response: retrofit2.Response<Vehicle>) {
                if (response.isSuccessful) {
                    val vehicle = response.body()
                    if (vehicle != null) {
                        vehicleList.clear()
                        vehicleList.add(vehicle)
                        adapter.notifyDataSetChanged()
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        intent.putExtra("vId", vehicleNo)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Vehicle Not Found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Vehicle Not Found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Vehicle>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
