package com.h5190001.flo

import android.app.Application

class FloApp: Application() {

    var instance: com.h5190001.flo.FloApp? = null

    fun getApp(): com.h5190001.flo.FloApp? {
        if (instance == null) {
            instance = this
        }
        return instance
    }
}