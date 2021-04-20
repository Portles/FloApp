package com.h5190001.flo

import android.app.Application

class Flo: Application() {

    var instance: com.h5190001.flo.Flo? = null

    fun getApp(): com.h5190001.flo.Flo? {
        if (instance == null) {
            instance = this
        }
        return instance
    }
}