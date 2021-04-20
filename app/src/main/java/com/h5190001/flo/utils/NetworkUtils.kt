package com.h5190001.flo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.h5190001.flo.R

class NetworkUtils: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i( context.resources.getString(R.string.network_internet), context.resources.getString(R.string.network_check_cellular) )
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i( context.resources.getString(R.string.network_internet), context.resources.getString(R.string.network_check_wifi) )
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i( context.resources.getString(R.string.network_internet), context.resources.getString(R.string.network_check_ethernet) )
                    return true
                }
            }
        }
        return false
    }
}