package com.h5190001.flo.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.h5190001.flo.R
import com.h5190001.flo.utils.AlertboxUtil
import com.h5190001.flo.utils.NetworkUtils

class SplashActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun init() {
        checkNetwork()

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkNetwork() {
        val internet = NetworkUtils()

        val isOnline = internet.isOnline(applicationContext)

        if (isOnline) {
            StartDelay()
        } else {
            val alerts = AlertboxUtil()

            alerts.InternetAlertDialog(applicationContext, this@SplashActivity)
        }
    }

    private fun StartDelay() {
        val timerThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timerThread.start()
    }
}