package com.hackvita.pathmed.management

import com.google.gson.annotations.SerializedName

data class ResposeReq(
    @SerializedName("status") var status: String? = null
)
