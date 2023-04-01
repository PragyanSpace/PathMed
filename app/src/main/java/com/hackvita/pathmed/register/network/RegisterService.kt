package com.hackvita.pathmed.register.network

import com.hackvita.pathmed.register.model.RegisterRequestModel
import com.hackvita.pathmed.register.model.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.*

interface RegisterService
{
    @POST
    fun registerApiCall(
        @Url url: String,
        @Header("Authorization") Authorization: String?,
        @Body registerRequestModel: RegisterRequestModel
    ): Call<RegisterResponseModel>
}
