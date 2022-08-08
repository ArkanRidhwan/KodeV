package com.example.extend1.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        binding.btmNavAdmin.setupWithNavController(navController)
        binding.btmNavUser.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeAdminFragment, R.id.profileAdminFragment -> {
                    binding.btmNavAdmin.visibility = View.VISIBLE
                }
                R.id.homeUserFragment, R.id.profileUserFragment, R.id.courseUserFragment -> {
                    binding.btmNavUser.visibility = View.VISIBLE
                }
                else -> {
                    binding.btmNavUser.visibility = View.GONE
                    binding.btmNavAdmin.visibility = View.GONE
                }
            }
        }
    }
}