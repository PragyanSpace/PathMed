package com.hackvita.pathmed.hospitalDetail.ui


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hackvita.pathmed.hospitalDetail.viewmodel.HospitalDetailViewmodel
import com.hackvita.pathmed.R
import com.hackvita.pathmed.databinding.ActivityHospitalDetailBinding
import com.hackvita.pathmed.hospitalDetail.model.AppointmentRequestModel
import com.hackvita.pathmed.hospitalDetail.viewmodel.HospitalDetailViewmodel
import com.hackvita.pathmed.utility.PrefUtil
import java.util.*

class HospitalDetail : AppCompatActivity() {

    lateinit var binding: ActivityHospitalDetailBinding
    private var viewModel: HospitalDetailViewmodel? = null
    lateinit var hosId: String
    lateinit var date: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_hospital_detail)
        viewModel = ViewModelProvider(this).get(HospitalDetailViewmodel::class.java)
        hosId=intent.getStringExtra("HOSPITAL_ID").toString()
        observeApiResponse()
        observeAppointmentApiResponse()
        callHospitalDetailApi()
        initListener()

    }

    private fun initListener() {
        binding.bookBtn.setOnClickListener {
            // Get the current date
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Set the dateEditText text to the selected date
                    date="$dayOfMonth-${monthOfYear + 1}-$year"
                    Log.d("date",date)
                    callAppointmentApi()
                },
                year,
                month,
                day
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }
    }

    private fun callHospitalDetailApi() {
        val token =
            PrefUtil(this@HospitalDetail).sharedPreferences?.getString(PrefUtil.TOKEN, "")
        viewModel?.callHospitalDetail(token,hosId)
    }


    private fun observeApiResponse() {
        viewModel?.hospitalResponseMutableLiveData?.observe(this, Observer {
            if(it.success==true)
            {
                var hosContact=""
                for (name in it.hospital?.contactNumber!!) {
                    hosContact+="$name , "
                }
                var hosDept=""
                for (name in it.hospital?.departments!!) {
                    hosDept+="$name , "
                }
                binding.name=it.hospital?.name.toString()
                binding.email=it.hospital?.email.toString()
                binding.contact=hosContact.trim()
                binding.departments=hosDept.trim()
                binding.address=it.hospital?.address?.city.toString()
            }
        })
    }

    private fun callAppointmentApi() {
        val token =
            PrefUtil(this@HospitalDetail).sharedPreferences?.getString(PrefUtil.TOKEN, "")
        val id =
            PrefUtil(this@HospitalDetail).sharedPreferences?.getString(PrefUtil.ID, "")
        var appointmentRequestModel=AppointmentRequestModel(hosId,id,date)
        viewModel?.callRequestAppointment(token,appointmentRequestModel)
    }


    private fun observeAppointmentApiResponse() {
        viewModel?.appointmentResponseMutableLiveData?.observe(this, Observer {
            if(it.success==true)
            {
                binding.bookBtn.visibility= View.GONE
                binding.message=it.message.toString()
                binding.appointmentTV.visibility=View.VISIBLE
            }
        })
    }


}