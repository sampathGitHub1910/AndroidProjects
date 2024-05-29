package com.example.project05

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VehicleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_detail)

        val intent = getIntent()
        val vehicleNo = intent.getStringExtra("vno")
        val vehicleName = intent.getStringExtra("vname")
        val bc = intent.getStringExtra("bc")
        val gr = intent.getStringExtra("gr")
        val temp = intent.getStringExtra("temp")
        val p = intent.getStringExtra("p")


        // Set the details to TextViews
        findViewById<TextView>(R.id.vehicleNoTextView).text = vehicleNo
        findViewById<TextView>(R.id.vehicleNameTextView).text = vehicleName
        findViewById<TextView>(R.id.batteryConditionTextView).text = bc
        findViewById<TextView>(R.id.gasRemainingTextView).text = gr
        findViewById<TextView>(R.id.temperatureTextView).text = temp
        findViewById<TextView>(R.id.pressureTextView).text = p
    }
}