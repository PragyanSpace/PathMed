package com.hackvita.pathmed.hospitalDetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hackvita.pathmed.hospitalDetail.model.AppointmentRequestModel
import com.hackvita.pathmed.hospitalDetail.model.AppointmentResponseModel
import com.hackvita.pathmed.hospitalDetail.model.HospitalResponseModel
import com.hackvita.pathmed.hospitalDetail.repository.HospitalDetailRepository

class HospitalDetailViewmodel(): ViewModel() {

    private val repository = HospitalDetailRepository()
    val showProgress: LiveData<Boolean>
    val errorMessage: LiveData<String>
    val hospitalResponseMutableLiveData: LiveData<HospitalResponseModel>
    val appointmentResponseMutableLiveData: LiveData<AppointmentResponseModel>


    init {
        this.showProgress = repository.showProgress
        this.errorMessage = repository.errorMessage
        this.hospitalResponseMutableLiveData = repository.hospitalResponseMutableLiveData
        this.appointmentResponseMutableLiveData = repository.appointmentResponseMutableLiveData
    }

    fun callHospitalDetail(token: String?, hospitalId: String) {
        repository.hospitalDetailApiCall(token, hospitalId)
    }

    fun callRequestAppointment(token: String?, appointmentRequestModel: AppointmentRequestModel) {
        repository.appointmentApiCall(token, appointmentRequestModel)
    }


}