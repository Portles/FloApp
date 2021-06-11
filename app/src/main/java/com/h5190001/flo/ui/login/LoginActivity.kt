package com.h5190001.flo.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.h5190001.flo.R
import com.h5190001.flo.ui.categories.CategoryActivity
import com.h5190001.flo.databinding.ActivityLoginBinding
import com.h5190001.flo.data.models.UserResponse
import com.h5190001.flo.data.models.UserResponseItem
import com.h5190001.flo.utils.AlertboxUtil.CustomAlertDialog
import com.h5190001.flo.utils.ObjectUtil.objectToString

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    var userViewModel: UserViewModel?=null
    var users: UserResponse? = null
    var loggedUser: UserResponseItem? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        initBinding()
        getUsers()
        initButtonAction()
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initButtonAction() {
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

        val validation: Boolean = validate(email ,password)

        if (validation) {
            val intent = Intent(this@LoginActivity, CategoryActivity::class.java)
            intent.putExtra(getString(R.string.loggedUser), objectToString(loggedUser))
            startActivity(intent)
            finish()
        }
    }

    private fun validate(email: String, password: String): Boolean {
        users.let {
            for (user in users!!) {
                if(user.email == email && user.password != password) {
                    CustomAlertDialog(this@LoginActivity,getString(R.string.wrong_pass_title),getString(R.string.wrong_pass_message), getString(R.string.wrong_pass_neg_button))
                    return false
                } else if(user.email == email && user.password == password) {
                    loggedUser = user
                    return true
                }
            }
        }
        CustomAlertDialog(this@LoginActivity,getString(R.string.log_alert_empty_places_title),getString(R.string.log_alert_empty_places_message), getString(R.string.log_alert_empty_places_neg_button))
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
}