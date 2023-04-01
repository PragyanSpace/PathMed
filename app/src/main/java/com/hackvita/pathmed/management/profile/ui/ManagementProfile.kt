package com.hackvita.pathmed.management.profile.ui

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.hackvita.pathmed.databinding.FragmentProfileBinding
import com.hackvita.pathmed.databinding.FragmentProfileManagementBinding
import com.hackvita.pathmed.management.profile.viewmodel.ProfileViewmodel
import com.hackvita.pathmed.utility.PrefUtil

class ManagementProfile : Fragment() {
    lateinit var binding:FragmentProfileManagementBinding
    lateinit var viewmodel: ProfileViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileManagementBinding.inflate(layoutInflater, container, false)
        viewmodel = ViewModelProvider(this).get(ProfileViewmodel::class.java)
        observeUserApiCall()
        callUserDetailApi()


        return binding.root
    }

    private fun callUserDetailApi() {
        val token= PrefUtil(requireContext()).sharedPreferences?.getString(PrefUtil.TOKEN, "")
//        val id= PrefUtil(requireContext()).sharedPreferences?.getString(PrefUtil.ID, "")
        val id= "6427d2141cda03833e4edac5"
        viewmodel.callUserApi(token,id)
    }

    private fun observeUserApiCall() {
        viewmodel.userResponseMutableLiveData.observe(viewLifecycleOwner, Observer {
            binding.name=it.hospital?.name
            var contact=""
            if(it.hospital?.contactNumber?.isNotEmpty()==true) {
                for (i in it.hospital?.contactNumber!!) {
                    contact+="$i,"
                }
            }
            binding.contact=contact
            binding.email=it.hospital?.email
            var dept=""
//            if(it.hospital?.departments?.isNotEmpty()==true) {
//                for (i in it.hospital?.departments!!.get(0).deptName!!) {
//                    dept+="$i,"
//                }
//            }
            dept=it.hospital?.departments!!.get(0).deptName!!
            binding.departments=dept
            binding.address="${it.hospital!!.address?.city},${it.hospital!!.address?.district},${it.hospital!!.address?.state},${it.hospital!!.address?.pinCode}"
        })
    }
}