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
                logUser()
            }
        }
    }

    private fun logUser() {
        val email: String = binding.editTextTextEmailAddress.text.toString()
        val password: String = binding.editTextTextPassword.text.toString()

        val valitadion: Boolean = validate(email ,password)

        if (valitadion) {
            val intent = Intent(this@LoginActivity,CategoryActivity::class.java)
            startActivity(intent)
            finish()
        } else {

        }
    }

    private fun validate(email: String, password: String): Boolean {

        return true
    }

    private fun setBinds() {
        binding.editTextTextEmailAddress
    }
}