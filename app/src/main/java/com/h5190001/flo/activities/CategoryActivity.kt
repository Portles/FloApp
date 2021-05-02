package com.h5190001.flo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.h5190001.flo.R
import com.h5190001.flo.databinding.ActivityCategoryBinding
import com.h5190001.flo.utils.AlertboxUtil
import java.util.*
import kotlin.collections.ArrayList

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    var list: ArrayList<String>? = null
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setBinds()
        setRecyclerViewData()
        listenSearchBar()
    }

    private fun setBinds() {


        binding.categorySearchView
        binding.categoryRecyclerView
    }

    private fun setRecyclerViewData() {
        list = ArrayList()
        list!!.add("Muhammet")
        list!!.add("Çağatay")
        list!!.add("Nisa")
        list!!.add("Berat")
        adapter = ArrayAdapter(this, R.layout.activity_category, list!!)

    }

    private fun listenSearchBar() {
        binding.apply {
            categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(list!!.contains(query.toString().toLowerCase(Locale("TR-tr")))){
                        adapter!!.filter.filter(query);
                    }else{
                        Toast.makeText(applicationContext, "Eşleşme Bulunamadı",Toast.LENGTH_SHORT).show();
                    }
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AlertboxUtil.QuitAlertDialog(applicationContext, this@CategoryActivity)
    }
}