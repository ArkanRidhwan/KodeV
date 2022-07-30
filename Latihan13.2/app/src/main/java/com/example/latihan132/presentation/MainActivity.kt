package com.example.latihan132.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.latihan132.R
import com.example.latihan132.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this@MainActivity, factory)[MainViewModel::class.java]

        val kartunAdapter = MainAdapter()
        viewModel.getKartun().observe(this){
            if(it != null){
                it.let {
                    kartunAdapter.setData(it)
                }
            }
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = kartunAdapter
        }
    }
}