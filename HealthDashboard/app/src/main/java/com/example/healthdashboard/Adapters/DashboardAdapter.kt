package com.example.healthdashboard.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthdashboard.Model.Vehicle
import com.example.healthdashboard.R

class DashboardAdapter(private val vehicles: List<Vehicle>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicleNo: TextView = itemView.findViewById(R.id.vehicle_number_input)
        val vehicleName: TextView = itemView.findViewById(R.id.vehicle_name_input)
        val batteryCondition: TextView = itemView.findViewById(R.id.battery_condition_input)
        val gasRemaining: TextView = itemView.findViewById(R.id.gas_remaining_input)
        val temperature: TextView = itemView.findViewById(R.id.temperature_input)
        val pressure: TextView = itemView.findViewById(R.id.pressure_input)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_register_vehical_actitity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.vehicleNo.text = vehicle.vehicleNo
        holder.vehicleName.text = vehicle.vehicleName
        holder.batteryCondition.text = vehicle.batteryCondition
        holder.gasRemaining.text = vehicle.gasRemaining
        holder.temperature.text = vehicle.temperature
        holder.pressure.text = vehicle.pressure
    }

    override fun getItemCount() = vehicles.size
}
