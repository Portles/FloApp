package com.h5190001.flo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190001.flo.R
import com.h5190001.flo.adapters.CategoryRecyclerViewAdapter
import com.h5190001.flo.databinding.ActivityCategoryBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.utils.AlertboxUtil
import com.h5190001.flo.utils.MESSAGE_TYPE
import com.h5190001.flo.utils.ToastUtil
import java.util.*
import kotlin.collections.ArrayList

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    var list: ArrayList<String>? = null
    var categoryList= arrayListOf("Kadın", "Erkek")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setBinds()
        setRecyclerViewData()
        listenSearchBar()
    }

    private fun setBinds() {
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categorySearchView
        binding.categoryRecyclerview
    }

    private fun setRecyclerViewData() {
        binding.apply {
            categoryAdapter = CategoryRecyclerViewAdapter(categoryList,object : ItemClickListener {
                override fun onDelete(position: Int) {
                    categoryList.removeAt(position)
                    categoryAdapter.notifyDataSetChanged()
                }
                override fun onItemClick(position: Int) {
                    //Toast.makeText(applicationContext,categoryList.get(position),Toast.LENGTH_SHORT).show()
                }
            })
            binding.categoryRecyclerview.adapter = categoryAdapter
            categoryRecyclerview.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun listenSearchBar() {
        binding.apply {
            categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(list!!.contains(query.toString().toLowerCase(Locale("TR-tr")))){
                        //categoryAdapter.filter(query);
                    }else{
                        ToastUtil.BuildToast(applicationContext, query!!.toString() ,MESSAGE_TYPE.SHORT_MESSAGE) //DİKKAT ÇÖKERTEBİLİR
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