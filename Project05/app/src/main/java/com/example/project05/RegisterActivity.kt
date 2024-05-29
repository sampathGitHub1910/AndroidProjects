package com.example.project05

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project05.models.Vehicle
import com.example.project05.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var vehicleNoEditText: EditText
    private lateinit var vehicleNameEditText: EditText
    private lateinit var batteryConditionEditText: EditText
    private lateinit var gasRemainingEditText: EditText
    private lateinit var temperatureEditText: EditText
    private lateinit var pressureEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        vehicleNoEditText = findViewById(R.id.vehicleNoEditText)
        vehicleNameEditText = findViewById(R.id.vehicleNameEditText)
        batteryConditionEditText = findViewById(R.id.batteryConditionEditText)
        gasRemainingEditText = findViewById(R.id.gasRemainingEditText)
        temperatureEditText = findViewById(R.id.temperatureEditText)
        pressureEditText = findViewById(R.id.pressureEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val vehicle = Vehicle(
                vehicle_no = vehicleNoEditText.text.toString(),
                vehicle_name = vehicleNameEditText.text.toString(),
                battery_condition = batteryConditionEditText.text.toString(),
                gas_remaining = gasRemainingEditText.text.toString(),
                temperature = temperatureEditText.text.toString(),
                pressure = pressureEditText.text.toString()
            )
            registerVehicle(vehicle)
        }
    }

    private fun registerVehicle(vehicle: Vehicle) {
        RetrofitClient.vehicleApi.registerVehicle(vehicle).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Vehicle registered successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}