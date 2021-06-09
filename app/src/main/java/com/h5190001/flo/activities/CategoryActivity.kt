package com.h5190001.flo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import com.h5190001.flo.adapters.CategoryRecyclerViewAdapter
import com.h5190001.flo.category.CategoryViewModel
import com.h5190001.flo.databinding.ActivityCategoryBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.utils.AlertboxUtil
import java.util.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.utils.ObjectUtil.objectToString
import com.h5190001.flo.utils.ProgressDialogUtil.DissmisDialog
import com.h5190001.flo.utils.ProgressDialogUtil.ShowDialog

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    var categoryViewModel: CategoryViewModel?=null
    private var category = CategoryResponse() //TODO BİLMİYORUM

    //TODO PROGRESSDIALOG EKLENECEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setBinds()
        ShowDialog(this@CategoryActivity)
        setRecyclerViewData()
    }

    private fun setBinds() {
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setRecyclerViewData() {
        categoryViewModel = CategoryViewModel()

        categoryViewModel?.apply {

            categorysLiveData.observe(this@CategoryActivity, Observer {
                it.run {
                    Log.e("Nxioterya","observe: "+it.toString())
                    setCategoryRecyclerView(this)
                    DissmisDialog()
                    category = it
                    SearchButton()
                }
            })

            error.observe(this@CategoryActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                    Log.e("Nxioterya","Error verdik")
                }
            })

            loading?.observe(this@CategoryActivity, Observer {

                //Handle loading
            })
        }
    }

    private fun setCategoryRecyclerView(cat: CategoryResponse) {
        binding.apply {
            categoryAdapter = CategoryRecyclerViewAdapter(cat,object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(applicationContext,cat[position].categoryName,Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@CategoryActivity, ListActivity::class.java)
                    val data: String = objectToString(cat[position].Items)
                    intent.putExtra("list",data) //TODO TIRNAKLARI DUZELT
                    startActivity(intent)
                }
            })
            binding.categoryRecyclerview.adapter = categoryAdapter
            categoryRecyclerview.layoutManager = GridLayoutManager(applicationContext,2) //TODO İKİYİ ENUM YAP
        }
    }

    private fun ArraylistToList(cat: CategoryResponse): List<CategoryResponse> {
        val arrList = listOf(cat)
        return arrList
    }

    private fun SearchButton() {
        binding.apply {
            searchButton.setOnClickListener {
                listenSearchBar()
            }
        }
    }

    private fun filter(search: String?) {
        search?.let {
            category.let {
                val filterCategoryList = it.filter { it.categoryName!!.contains(search) }
                setCategoryRecyclerView(filterCategoryList as CategoryResponse)
            }
        }
    }

    private fun listenSearchBar() {
        binding.apply {
            category.let {
                val data: List<CategoryResponse> = ArraylistToList(category)
                categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if(data.contains(query.toString().toLowerCase(Locale("TR-tr")))){ //BAŞINA ARAMA STRİNG ARRAY EKLE //TODO
                            filter(query);
                        }
                        return false
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }

        }
    }

    override fun onBackPressed() {
        AlertboxUtil.QuitAlertDialog(applicationContext, this@CategoryActivity)
    }
}
