package com.hackvita.pathmed.management.home.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.hackvita.pathmed.R
import com.hackvita.pathmed.databinding.AppointmentRvItemBinding
import com.hackvita.pathmed.databinding.HospitalsItemBinding
import com.hackvita.pathmed.hospitalDetail.ui.HospitalDetail
import com.hackvita.pathmed.management.AppointmentDetail
import com.hackvita.pathmed.management.home.model.AppointmentResponseData
import com.hackvita.pathmed.management.home.model.Appointments
import com.hackvita.pathmed.patient.home.model.ResHospital

class HomeAdapter(
    var context: Context,
    var hospitals: ArrayList<Appointments>?
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val binding: AppointmentRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(hospitalData: Appointments?, context: Context, position: Int) {

            binding.name.text = hospitalData?.user?.name.toString()
            binding.address.text = hospitalData?.appointmentDate?.substring(0,10).toString()

            binding.root.setOnClickListener {
                var intent = Intent(context, AppointmentDetail::class.java)
                intent.putExtra("name", hospitalData?.user?.name)
                intent.putExtra("dob", hospitalData?.user?.dob)
                intent.putExtra("blood", hospitalData?.user?.bloodGroup)
                intent.putExtra("email", hospitalData?.user?.email)
                intent.putExtra("contact", hospitalData?.user?.phoneNumber)
                intent.putExtra("id", hospitalData?.Id)
                startActivity(context, intent, null)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AppointmentRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        hospitals?.get(position).let {
            holder.bindView(it, context, position)
        }
    }

    private fun handleClickOfView(
        holder: ViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {
        return hospitals?.size ?: 0;
    }


}