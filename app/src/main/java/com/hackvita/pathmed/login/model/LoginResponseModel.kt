package com.hackvita.pathmed.login.model


import com.google.gson.annotations.SerializedName


data class LoginResponseModel(
    // TODO:  will change afterwards
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("user"    ) var user    : User?    = User(),
    @SerializedName("token"   ) var token   : String?  = null

)