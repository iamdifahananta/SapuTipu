package com.project.saputipuapp.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.project.saputipuapp.R
import com.project.saputipuapp.databinding.ActivityRegisterBinding
import com.project.saputipuapp.ui.login.LoginActivity
import com.project.saputipuapp.utils.ApiCallbackString

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        setButtonRegister()
        setButtonLoginActivity()
    }

    private fun setButtonRegister() {
        binding.btnRegister.setOnClickListener {
            val name = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            showLoading(true)

            viewModel.register(email, name, password, object : ApiCallbackString {
                override fun onResponse(success: Boolean, message: String) {
                    val filterMessage = message.replace(",", " ")
                    val cleanedMessage = filterMessage.filter { it.isLetterOrDigit() || it.isWhitespace() }
                    if (success) {
                        showLoading(false)
                        Toast.makeText(this@RegisterActivity, getString(R.string.register_success), Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        showLoading(false)
                        Toast.makeText(this@RegisterActivity, getString(R.string.register_failed) + " " + cleanedMessage, Toast.LENGTH_LONG).show()
                    }
                }

            })
        }
    }

    private fun setButtonLoginActivity() {
        binding.btnLoginActivity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}