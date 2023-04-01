package com.hackvita.pathmed.hospitalDetail.network

import com.hackvita.pathmed.hospitalDetail.model.AppointmentRequestModel
import com.hackvita.pathmed.hospitalDetail.model.AppointmentResponseModel
import com.hackvita.pathmed.hospitalDetail.model.HospitalResponseModel
import retrofit2.Call
import retrofit2.http.*

interface HospitalDetailService {
    @GET
    fun hospitalDetailApiCall(
        @Header("Authorization") Authorization: String?,
        @Url url: String
    ): Call<HospitalResponseModel>

    @POST("api/v1/user/reqApt")
    fun requestAppointmentApiCall(
        @Header("Authorization") Authorization: String?,
        @Body appointmentRequestModel: AppointmentRequestModel
    ): Call<AppointmentResponseModel>

}
