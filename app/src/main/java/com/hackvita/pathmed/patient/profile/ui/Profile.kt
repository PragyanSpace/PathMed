package com.hackvita.pathmed.patient.profile.ui

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
import com.hackvita.pathmed.patient.profile.viewmodel.ProfileViewmodel
import com.hackvita.pathmed.utility.PrefUtil
import com.journeyapps.barcodescanner.BarcodeEncoder

class Profile : Fragment() {
    lateinit var binding:FragmentProfileBinding
    lateinit var viewmodel:ProfileViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        viewmodel = ViewModelProvider(this).get(ProfileViewmodel::class.java)
        observeUserApiCall()
        callUserDetailApi()


        return binding.root
    }

    private fun callUserDetailApi() {
        val token= PrefUtil(requireContext()).sharedPreferences?.getString(PrefUtil.TOKEN, "")
        val id= PrefUtil(requireContext()).sharedPreferences?.getString(PrefUtil.ID, "")

        viewmodel.callUserApi(token,id)
    }

    private fun observeUserApiCall() {
        viewmodel.userResponseMutableLiveData.observe(viewLifecycleOwner, Observer {
            binding.name=it.user?.name
            binding.contact=it.user?.email
            binding.email=it.user?.email
            binding.dob=it.user?.dob
            binding.blood=it.user?.bloodGroup
            binding.qr.setImageBitmap(generateQr(it.user?.id))
        })
    }

    private fun generateQr(id:String?):Bitmap? {
        val mWriter = MultiFormatWriter()
        try {
            val mMatrix = mWriter.encode(id, BarcodeFormat.QR_CODE, 400, 400);
            val mEncoder = BarcodeEncoder();
            return mEncoder.createBitmap(mMatrix)
        } catch (e: WriterException) {
            e.printStackTrace();
        }
        return null
    }
}