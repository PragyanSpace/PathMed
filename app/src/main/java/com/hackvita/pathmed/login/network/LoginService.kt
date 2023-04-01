package com.hackvita.pathmed.login.network

import com.hackvita.pathmed.login.model.LoginRequestModel
import com.hackvita.pathmed.login.model.LoginResponseModel
import retrofit2.Call
import retrofit2.http.*

interface LoginService  {
    @POST
    fun loginApiCall(
        @Url url: String,
        @Header("Authorization") Authorization: String?,
        @Body loginRequestModel: LoginRequestModel
    ): Call<LoginResponseModel>

}
