package com.abdulaziz.tala.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.abdulaziz.tala.R
import com.abdulaziz.tala.databinding.ActivityMainBinding
import com.abdulaziz.tala.managers.Listeners
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)
        setupObservers()
    }

    fun getNavController() = binding.container.findNavController()

    private fun setupObservers() {
        Listeners.errorLiveData.observe(this){
            if (it.hasBeenHandled.not()){
                Toast.makeText(this@MainActivity, it.getContentIfNotHandled(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}