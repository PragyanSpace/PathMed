package com.hackvita.pathmed.management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hackvita.pathmed.R
import com.hackvita.pathmed.databinding.ActivityManagementBinding
import com.hackvita.pathmed.databinding.ActivityPatientBinding

class ManagementActivity : AppCompatActivity() {
    lateinit var binding:ActivityManagementBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_management)
        setupViews()

    }
    private fun setupViews() {
        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        setupSmoothBottomMenu()
    }
    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.management_menu_bottom_navbar)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }
}