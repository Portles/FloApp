package com.h5190001.flo.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.h5190001.flo.R
import com.h5190001.flo.databinding.ActivityLoginBinding
import com.h5190001.flo.databinding.ActivitySplashBinding
import com.h5190001.flo.ui.login.LoginActivity
import com.h5190001.flo.utils.AlertdialogUtil
import com.h5190001.flo.utils.AlertdialogUtil.InternetAlertDialog
import com.h5190001.flo.utils.AlertdialogUtil.QuitAlertDialog
import com.h5190001.flo.utils.NetworkUtil
import com.h5190001.flo.utils.enums.ConnectionTypeEnum

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val networkMonitor = NetworkUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        initBinding()
        checkInternet()
    }

    private fun initBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun checkInternet() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionTypeEnum.Wifi -> {
                                startDelay()
                            }
                            ConnectionTypeEnum.Cellular -> {
                                startDelay()
                            }
                            else -> { }
                        }
                    }
                    false -> {
                        InternetAlertDialog(applicationContext, this@SplashActivity)
                    }
                }
            }
        }
    }

    private fun startDelay() {
        val timerThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timerThread.start()
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }
    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}