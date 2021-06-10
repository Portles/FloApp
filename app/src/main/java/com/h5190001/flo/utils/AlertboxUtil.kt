package com.h5190001.flo.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.h5190001.flo.R

object AlertboxUtil {

    fun InternetAlertDialog(context: Context, activity: Activity) {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle(context.resources.getString(R.string.internet_dialog_header))
        builder.setMessage(context.resources.getString(R.string.internet_dialog_message))
        builder.setPositiveButton(
            context.resources.getString(R.string.internet_dialog_positive)
        ) { intf: DialogInterface, i: Int ->
            val InternetIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
            activity.startActivity(InternetIntent)
            intf.dismiss()
            activity.finish()
        }
        builder.setNegativeButton(
            context.resources.getString(R.string.internet_dialog_negative)
        ) { intf: DialogInterface, i: Int ->
            intf.dismiss()
            activity.finish()
        }
        builder.show()
    }

    fun QuitAlertDialog(context: Context, activity: Activity) {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle(context.resources.getString(R.string.quit_dialog_header))
        builder.setMessage(context.resources.getString(R.string.quit_dialog_message))
        builder.setPositiveButton(
            context.resources.getString(R.string.quit_dialog_positive)
        ) { intf: DialogInterface, i: Int ->
            intf.dismiss()
        }
        builder.setNegativeButton(context.resources.getString(R.string.quit_dialog_negative)
        ) { intf: DialogInterface, i: Int ->
            intf.dismiss()
            activity.finish()
        }
        builder.show()
    }

    fun CustomAlertDialog(activity: Activity, title: String, message: String, neg: String) {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(
            neg
        ) { intf: DialogInterface, i: Int ->
            intf.dismiss()
        }
        builder.show()
    }

}