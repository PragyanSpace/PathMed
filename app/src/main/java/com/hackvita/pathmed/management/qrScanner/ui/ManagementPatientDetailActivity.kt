package com.hackvita.pathmed.management.qrScanner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hackvita.pathmed.R
import com.hackvita.pathmed.databinding.ActivityManagementPatientDetailBinding

class ManagementPatientDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityManagementPatientDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_management_patient_detail)
        binding.name=intent.getStringExtra("name")
        binding.contact=intent.getStringExtra("contact")
        binding.email=intent.getStringExtra("email")
        binding.dob=intent.getStringExtra("dob")
        binding.blood=intent.getStringExtra("blood")
    }
}