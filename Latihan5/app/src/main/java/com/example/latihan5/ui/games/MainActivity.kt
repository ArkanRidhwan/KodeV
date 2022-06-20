package com.example.latihan5.ui.games

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.latihan5.databinding.ActivityMainBinding
import com.example.latihan5.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayResult()

        binding.button.setOnClickListener {
            displayResult()
        }
    }

    private fun displayResult() {
        viewModel.addNumber()
        binding.textView.text = viewModel.number.toString()
    }
}