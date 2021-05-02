package com.h5190001.flo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190001.flo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setBinds()
        setButtonAction()
    }

    private fun setButtonAction() {
        setLoginButtonListener()
    }

    private fun setLoginButtonListener() {
        binding.apply {
            logButton.setOnClickListener() {
                val email = binding.editTextTextEmailAddress.text
                val password = binding.editTextTextPassword.text

                val intent = Intent(this@LoginActivity,CategoryActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setBinds() {


        binding.editTextTextEmailAddress
    }
}