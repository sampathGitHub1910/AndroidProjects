package com.example.healthdashboard.activities

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
import retrofit2.Response

class RegisterVehicalActitity : AppCompatActivity() {
    private lateinit var adapter: DashboardAdapter
    private lateinit var vehicleList: MutableList<Vehicle>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_vehical_actitity)

        val vehicleNo: EditText = findViewById(R.id.vehicle_number_input)
        val vehicleName: EditText = findViewById(R.id.vehicle_name_input)
        val batteryCondition: EditText = findViewById(R.id.battery_condition_input)
        val gasRemaining: EditText = findViewById(R.id.gas_remaining_input)
        val temperature: EditText = findViewById(R.id.temperature_input)
        val pressure: EditText = findViewById(R.id.pressure_input)

        val btn: Button = findViewById(R.id.register_vehicle_button)

        btn.setOnClickListener {
            if (vehicleNo.text.toString().isNotEmpty() && vehicleName.text.toString().isNotEmpty() && batteryCondition.text.toString().isNotEmpty() && gasRemaining.text.toString().isNotEmpty() && temperature.text.toString().isNotEmpty() && pressure.text.toString().isNotEmpty()){
                registerNewVehicle(vehicleNo.text.toString(), vehicleName.text.toString(), batteryCondition.text.toString(), gasRemaining.text.toString(), temperature.text.toString(), pressure.text.toString())
            } else{
                Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerNewVehicle(vehicleNo: String, vehicleName: String, batteryCondition: String, gasRemaining: String, temperature: String, pressure: String) {
        val newVehicle = Vehicle(vehicleNo, vehicleName, batteryCondition, gasRemaining, temperature, pressure)

        RetrofitClient.api.registerVehicle(newVehicle).enqueue(object : Callback<Vehicle>{
            override fun onResponse(call: Call<Vehicle>, response: Response<Vehicle>){
                if (response.isSuccessful) {
                    response.body()?.let { registeredVehicle ->
                        vehicleList.add(registeredVehicle)
                        adapter.notifyItemInserted(vehicleList.size - 1)
                        Toast.makeText(this@RegisterVehicalActitity, "Vehicle Registered Successfully", Toast.LENGTH_SHORT).show()
                    }
                    val intent = Intent(this@RegisterVehicalActitity, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@RegisterVehicalActitity, "Failed to Register Vehicle", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<Vehicle>, p1: Throwable) {
                Toast.makeText(this@RegisterVehicalActitity, "Failed to Register Vehicle", Toast.LENGTH_SHORT).show()
            }
        })
    }
}