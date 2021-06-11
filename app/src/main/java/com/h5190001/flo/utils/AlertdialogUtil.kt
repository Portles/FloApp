package com.h5190001.flo.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.h5190001.flo.R
import com.h5190001.flo.ui.list.ListActivity.Companion.list
import com.h5190001.flo.ui.list.ListActivity.Companion.listAdapter
import com.h5190001.flo.data.models.Item

object AlertdialogUtil {
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
