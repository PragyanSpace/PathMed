package com.hackvita.pathmed.management.home.network

import com.hackvita.pathmed.management.AppointmentResponseData2
import com.hackvita.pathmed.management.ResposeReq
import com.hackvita.pathmed.management.home.model.AppointmentResponseData
import retrofit2.Call
import retrofit2.http.*

interface HomeNetwork  {

    @GET
    fun callAppointmentApi(
        @Header("Authorization") Authorization: String?,
        @Url url:String
    ): Call<AppointmentResponseData>

    @POST
    fun callResponse(
        @Header("Authorization") Authorization: String?,
        @Url url:String,
        @Body responseReq: ResposeReq
    ): Call<AppointmentResponseData2>

}