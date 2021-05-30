package com.h5190001.flo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.h5190001.flo.databinding.ActivityLoginBinding
import com.h5190001.flo.user.UserViewModel

class LoginActivity : AppCompatActivity() {
    //LOGO KOY //TODO
    private lateinit var binding: ActivityLoginBinding

    var userViewModel: UserViewModel?=null

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

        //userViewModel= UserViewModel()

        //val valitadion: Boolean = validate(email ,password)

        //if (valitadion) {
            val intent = Intent(this@LoginActivity,CategoryActivity::class.java)
            startActivity(intent)
            finish()
        //} else {

        //}
    }

    private fun validate(email: String, password: String): Boolean {
        userViewModel?.apply {

            allUsersLiveData?.observe(this@LoginActivity, Observer {

                it.run {


                }
            })

            error?.observe(this@LoginActivity, Observer {

            })

            loading?.observe(this@LoginActivity, Observer {

                //Handle loading
            })
        }
        return true
    }

    private fun setBinds() {
        binding.editTextTextEmailAddress
    }
}