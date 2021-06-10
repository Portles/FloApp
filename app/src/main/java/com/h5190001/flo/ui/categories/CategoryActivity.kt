package com.h5190001.flo.ui.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import com.h5190001.flo.databinding.ActivityCategoryBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.utils.AlertboxUtil
import java.util.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.h5190001.flo.R
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.models.CategoryResponseItem
import com.h5190001.flo.ui.list.ListActivity
import com.h5190001.flo.utils.ObjectUtil.objectToString
import com.h5190001.flo.utils.ProgressDialogUtil.DissmisDialog
import com.h5190001.flo.utils.ProgressDialogUtil.ShowDialog

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    var categoryViewModel: CategoryViewModel?=null
    var categoryList: List<CategoryResponseItem>? = null

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
                    Log.e(applicationContext.getResources().getString(R.string.login_debug),it.toString())
                    setCategoryRecyclerView(this)
                    DissmisDialog()
                    categoryList = it
                    setCategoryRecyclerView(categoryList!!)
                    SearchButton()
                }
            })

            error.observe(this@CategoryActivity, Observer {
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

            loading?.observe(this@CategoryActivity, Observer {

                //Handle loading
            })
        }
    }

    private fun setCategoryRecyclerView(categoryList: List<CategoryResponseItem>) {
        binding.apply {
            categoryAdapter = CategoryRecyclerViewAdapter(categoryList,object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(applicationContext,categoryList[position].categoryName,Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@CategoryActivity, ListActivity::class.java)
                    val data: String = objectToString(categoryList[position].Items)
                    intent.putExtra(applicationContext.getResources().getString(R.string.list),data)
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
            categoryList?.let {
                val filterCategoryList: List<CategoryResponseItem> = it.filter { it.categoryName!!.toLowerCase(Locale(applicationContext.getResources().getString(R.string.language))).contains(search) }
                categoryAdapter.setData(filterCategoryList)
                categoryAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun listenSearchBar() {
        binding.apply {
                categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener //TODO BUTONA BAĞLA
                {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    filter(query.toString().toLowerCase(Locale(applicationContext.getResources().getString(R.string.language))))
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrBlank()) {
                        categoryList?.let { categoryAdapter.setData(it) }
                        categoryAdapter.notifyDataSetChanged()
                    }
                    return false
                }
                })
        }
    }

    override fun onBackPressed() {
        AlertboxUtil.QuitAlertDialog(applicationContext, this@CategoryActivity)
    }
}
