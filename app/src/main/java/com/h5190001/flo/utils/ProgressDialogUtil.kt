package com.h5190001.flo.utils

import android.app.Activity
import android.app.AlertDialog
import com.h5190001.flo.R

object ProgressDialogUtil {
    private lateinit var isdialog: AlertDialog
    fun ShowDialog(activity: Activity) {
        val inflate = activity.layoutInflater
        val dialogView = inflate.inflate(R.layout.progress_dialog ,null,)

        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }
    fun DissmisDialog() {
        isdialog.dismiss()
    }
}