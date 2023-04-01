package com.hackvita.pathmed.management.home.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hackvita.pathmed.RetrofitUtilClass
import com.hackvita.pathmed.management.AppointmentResponseData2
import com.hackvita.pathmed.management.ResposeReq
import com.hackvita.pathmed.management.home.model.AppointmentResponseData
import com.hackvita.pathmed.management.home.network.HomeNetwork
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {
    val showProgress = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val appointmentResponseModel = MutableLiveData<AppointmentResponseData>()
    val appointmentResponseModel2 = MutableLiveData<AppointmentResponseData2>()

    fun getAppointments(token: String, hosId: String) {

        val client = RetrofitUtilClass.getRetrofit()?.create(HomeNetwork::class.java)

        val url="api/v1/hospital/all-appointment/$hosId"
        val call = client?.callAppointmentApi(token, url)

        call?.enqueue(object : Callback<AppointmentResponseData?> {
            override fun onResponse(
                call: Call<AppointmentResponseData?>,
                response: Response<AppointmentResponseData?>
            ) {
                showProgress.postValue(false)
                val body = response.body()
                if (response.isSuccessful) {
                    body?.let {
                        appointmentResponseModel.postValue(it)
                    }
                } else {
                    val jObjError = JSONObject(response.errorBody()?.string())
                    errorMessage.postValue(jObjError.getString("message"))
                }
            }

            override fun onFailure(call: Call<AppointmentResponseData?>, t: Throwable) {
                showProgress.postValue(false)
                errorMessage.postValue("Server error please try after sometime")
                Log.i("ErrorCheck", "onFailure: ${t.message} ")
            }
        })


    }

    fun postResponse(token: String,id:String, body:ResposeReq) {

        val client = RetrofitUtilClass.getRetrofit()?.create(HomeNetwork::class.java)

        val url="api/v1/hospital/resApt/$id"
        val call = client?.callResponse(token, url,body)

        call?.enqueue(object : Callback<AppointmentResponseData2> {
            override fun onResponse(
                call: Call<AppointmentResponseData2?>,
                response: Response<AppointmentResponseData2>
            ) {
                showProgress.postValue(false)
                val body = response.body()
                if (response.isSuccessful) {
                    body?.let {
                        appointmentResponseModel2.postValue(it)
                    }
                } else {
                    val jObjError = JSONObject(response.errorBody()?.string())
                    errorMessage.postValue(jObjError.getString("message"))
                }
            }

            override fun onFailure(call: Call<AppointmentResponseData2>, t: Throwable) {
                showProgress.postValue(false)
                errorMessage.postValue("Server error please try after sometime")
                Log.i("ErrorCheck", "onFailure: ${t.message} ")
            }
        })


    }
}