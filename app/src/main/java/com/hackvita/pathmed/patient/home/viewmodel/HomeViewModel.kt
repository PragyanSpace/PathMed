package com.hackvita.pathmed.patient.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hackvita.pathmed.patient.home.model.HospitalRequestModel
import com.hackvita.pathmed.patient.home.model.HospitalResponseData
import com.hackvita.pathmed.patient.home.repository.HomeRepository

class HomeViewModel : ViewModel() {
    private val repository= HomeRepository()
    val showProgress: LiveData<Boolean>
    val errorMessage: LiveData<String>
    val hospitalResponse :LiveData<HospitalResponseData>

    init {
        this.showProgress = repository.showProgress
        this.errorMessage = repository.errorMessage
        this.hospitalResponse = repository.hospitalResponse
    }

    fun hospitalApiCall(token: String, hospitalRequestModel: HospitalRequestModel){
        repository.getHospitals(token, hospitalRequestModel)
    }
}