package com.hackvita.pathmed.patient.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hackvita.pathmed.login.model.LoginRequestModel
import com.hackvita.pathmed.login.model.LoginResponseModel
import com.hackvita.pathmed.patient.profile.model.UserDataResponseModel
import com.hackvita.pathmed.patient.profile.repository.ProfileRepository

class ProfileViewmodel(): ViewModel() {

    private val repository = ProfileRepository()
    val showProgress: LiveData<Boolean>
    val errorMessage: LiveData<String>
    val userResponseMutableLiveData: LiveData<UserDataResponseModel>


    init {
        this.showProgress = repository.showProgress
        this.errorMessage = repository.errorMessage
        this.userResponseMutableLiveData = repository.userResponseMutableLiveData
    }

    fun callUserApi(token: String?, id: String?) {
        repository.loginApiCall(token, id)
    }


}