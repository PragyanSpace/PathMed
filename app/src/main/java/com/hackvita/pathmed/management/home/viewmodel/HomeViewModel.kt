package com.hackvita.pathmed.management.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hackvita.pathmed.management.AppointmentResponseData2
import com.hackvita.pathmed.management.ResposeReq
import com.hackvita.pathmed.management.home.model.AppointmentResponseData
import com.hackvita.pathmed.management.home.model.AppointmentlRequestModel
import com.hackvita.pathmed.management.home.repository.HomeRepository

class HomeViewModel : ViewModel() {
    private val repository= HomeRepository()
    val showProgress: LiveData<Boolean>
    val errorMessage: LiveData<String>
    val appointmentResponse :LiveData<AppointmentResponseData>
    val appointmentResponse2 :LiveData<AppointmentResponseData2>

    init {
        this.showProgress = repository.showProgress
        this.errorMessage = repository.errorMessage
        this.appointmentResponse = repository.appointmentResponseModel
        this.appointmentResponse2 = repository.appointmentResponseModel2
    }

    fun hospitalApiCall(token: String, hosId:String){
        repository.getAppointments(token,hosId)
    }

    fun resApiCall(token: String, hosId:String,status:String){
        repository.postResponse(token,hosId,ResposeReq(status))
    }
}