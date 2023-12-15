package com.project.saputipuapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.saputipuapp.R
import com.project.saputipuapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}