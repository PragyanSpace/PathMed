package com.hackvita.pathmed.management.home.model

import com.google.gson.annotations.SerializedName


data class AppointmentlRequestModel (
    @SerializedName("city") var city : String?,
    @SerializedName("hospital") var hospital : String?
)