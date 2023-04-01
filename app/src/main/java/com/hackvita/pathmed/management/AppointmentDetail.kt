package com.hackvita.pathmed.management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBinderMapper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hackvita.pathmed.R
import com.hackvita.pathmed.databinding.ActivityAppointmentDetailBinding
import com.hackvita.pathmed.management.home.viewmodel.HomeViewModel
import com.hackvita.pathmed.utility.PrefUtil

class AppointmentDetail : AppCompatActivity() {
    lateinit var binding:ActivityAppointmentDetailBinding
    lateinit var token:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_appointment_detail)

        binding.name=intent.getStringExtra("name")
        binding.dob=intent.getStringExtra("dob")
        binding.blood=intent.getStringExtra("blood")
        binding.email=intent.getStringExtra("email")
        binding.contact=intent.getStringExtra("contact")
        val id=intent.getStringExtra("id")
        var viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        token = PrefUtil(this).sharedPreferences?.getString(PrefUtil.TOKEN, "").toString()
        observeApi()

        binding.accept.setOnClickListener {

            viewmodel.resApiCall(token,id.toString(),"A")
        }

        binding.reject.setOnClickListener {
            viewmodel.resApiCall(token,id.toString(),"R")
        }
    }

    private fun observeApi() {
        var viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewmodel?.appointmentResponse2?.observe(this, Observer {
            if (it.success == true) {
                binding.accept.visibility=View.GONE
                binding.reject.visibility=View.GONE
            }
        })
    }
    }