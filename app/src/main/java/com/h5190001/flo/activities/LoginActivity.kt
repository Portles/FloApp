package com.h5190001.flo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.h5190001.flo.R
import com.h5190001.flo.databinding.ActivityLoginBinding
import com.h5190001.flo.models.UserResponse
import com.h5190001.flo.user.UserViewModel

class LoginActivity : AppCompatActivity() {
    //LOGO KOY //TODO
    private lateinit var binding: ActivityLoginBinding

    var userViewModel: UserViewModel?=null
    var users: UserResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setBinds()
        getUsers()
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

        val intent = Intent(this@LoginActivity,CategoryActivity::class.java)
        startActivity(intent) // TODO BURAYI SİL
        finish()

        val valitadion: Boolean = validate(email ,password)

        if (valitadion) {
            val intent = Intent(this@LoginActivity,CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validate(email: String, password: String): Boolean {
        users.let {
            for (user in users!!) {
                if(user.email == email && user.password == password) {
                    return true
                }
            }
        }
        return false
    }

    private fun getUsers(){
        userViewModel= UserViewModel()
        userViewModel?.apply {

            allUsersLiveData.observe(this@LoginActivity, Observer {
                it.run {
                    Log.e(applicationContext.getResources().getString(R.string.login_debug),it.toString())
                    users = it
                }
            })

            error.observe(this@LoginActivity, Observer {

            })

            loading?.observe(this@LoginActivity, Observer {

                //Handle loading
            })
        }
    }

    private fun setBinds() {
        binding.editTextTextEmailAddress
    }
}