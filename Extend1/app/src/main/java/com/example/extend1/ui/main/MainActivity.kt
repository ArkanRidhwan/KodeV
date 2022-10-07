package com.example.extend1.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.extend1.R
import com.example.extend1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)
        binding.btmNavCompany.setupWithNavController(navController)
        binding.btmNavAdmin.setupWithNavController(navController)
        binding.btmNavStudent.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeCompanyFragment, R.id.profileCompanyFragment -> {
                    binding.btmNavCompany.visibility = View.VISIBLE
                }
                R.id.homeAdminFragment, R.id.profileAdminFragment, R.id.courseAdminFragment -> {
                    binding.btmNavAdmin.visibility = View.VISIBLE
                }
                R.id.homeStudentFragment, R.id.profileStudentFragment, R.id.courseStudentFragment -> {
                    binding.btmNavStudent.visibility = View.VISIBLE
                }
                else -> {
                    binding.btmNavCompany.visibility = View.GONE
                    binding.btmNavAdmin.visibility = View.GONE
                    binding.btmNavStudent.visibility = View.GONE
                }
            }
        }
    }
}