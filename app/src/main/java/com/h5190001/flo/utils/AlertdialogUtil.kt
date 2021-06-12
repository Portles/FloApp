package com.h5190001.flo.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import com.h5190001.flo.R
import com.h5190001.flo.ui.list.ListActivity.Companion.list
import com.h5190001.flo.ui.list.ListActivity.Companion.listAdapter
import com.h5190001.flo.data.models.Item

object AlertdialogUtil {
    fun InternetAlertDialog(context: Context, activity: Activity) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity)

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
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity)

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
        val builder = androidx.appcompat.app.AlertDialog.Builder(activity)

        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(
            neg
        ) { intf: DialogInterface, i: Int ->
            intf.dismiss()
        }
        builder.show()
    }

    fun BuildSortAlert(context: Context, activity: Activity, items: ArrayList<Item>) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(context.getString(R.string.sort_alert_title))
        val selection = arrayOf(context.getString(R.string.sort_alert_sort), context.getString(R.string.sort_alert_descending_sort))
        builder.setItems(selection) { dialog, pozisyon ->
            when (pozisyon) {
                0 -> { /* İsme göre artan */
                    items.sortBy { it.name }
                    list = items
                    listAdapter.notifyDataSetChanged()
                }
                1 -> { /* İsme göre azalan */
                    items.sortByDescending { it.name }
                    list = items
                    listAdapter.notifyDataSetChanged()
                }
            }
        }
        val dialog = builder.create()
        dialog.show ()
    }
}
