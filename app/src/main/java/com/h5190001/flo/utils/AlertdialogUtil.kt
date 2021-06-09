package com.h5190001.flo.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

object AlertdialogUtil {
    fun BuildSortAlert(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Sırala") //TODO STRİNGLE
        val selection = arrayOf("İsme göre artan", "İsme göre azalan")
        builder.setItems(selection) { dialog, pozisyon ->
            when (pozisyon) {
                0 -> { /* İsme göre artan */
                }
                1 -> { /* İsme göre azalan */
                }
            }
        }
        val dialog = builder.create()
        dialog.show ()
    }
}
