package com.h5190001.flo.utils

import android.app.Activity
import android.app.AlertDialog
import com.h5190001.flo.ui.list.ListActivity.Companion.list
import com.h5190001.flo.ui.list.ListActivity.Companion.listAdapter
import com.h5190001.flo.data.models.Item

object AlertdialogUtil {
    fun BuildSortAlert(activity: Activity, items: ArrayList<Item>) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Sırala") //TODO STRİNGLE
        val selection = arrayOf("İsme göre artan", "İsme göre azalan")
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
