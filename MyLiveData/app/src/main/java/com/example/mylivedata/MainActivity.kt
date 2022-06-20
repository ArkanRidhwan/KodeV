package com.example.mylivedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.nomor.observe(this) { data ->
            binding.textView.text = data.toString()
        }

        binding.button.setOnClickListener {
            mainViewModel._nomor.value = binding.editText.text.toString()
        }
    }
}