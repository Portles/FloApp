package com.h5190001.flo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190001.flo.adapters.CategoryRecyclerViewAdapter
import com.h5190001.flo.category.CategoryViewModel
import com.h5190001.flo.databinding.ActivityCategoryBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.utils.AlertboxUtil
import com.h5190001.flo.utils.MESSAGE_TYPE
import com.h5190001.flo.utils.ToastUtil
import java.util.*
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.utils.ObjectUtil.ObjeToJsonString
import com.h5190001.flo.utils.ObjectUtil.jsonStringToObje
import com.h5190001.flo.utils.ObjectUtil.jsonStringToObject
import com.h5190001.flo.utils.ObjectUtil.objectToString

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    var categoryViewModel: CategoryViewModel?=null
    private var list = listOf<String>() //TODO BİLMİYORUM

    //TODO PROGRESSDIALOG EKLENECEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setBinds()
        setRecyclerViewData()
    }

    private fun setBinds() {
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categorySearchView
        binding.categoryRecyclerview
    }

    private fun setRecyclerViewData() {
        categoryViewModel = CategoryViewModel()

        categoryViewModel?.apply {

            categorysLiveData.observe(this@CategoryActivity, Observer {
                it.run {
                    Log.e("Nxioterya","observe: "+it.toString())
                    setCategoryRecyclerView(this)
                    list = listCategoryNames(this)
                    listenSearchBar()
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
                    val intent = Intent(this@CategoryActivity,ListActivity::class.java)
                    val data: String = objectToString(cat[position].Items)
                    intent.putExtra("list",data) //TODO TIRNAKLARI DUZELT
                    startActivity(intent)
                    finish()
                }
            })
            binding.categoryRecyclerview.adapter = categoryAdapter
            categoryRecyclerview.layoutManager = GridLayoutManager(applicationContext,2) //TODO İKİYİ ENUM YAP
        }
    }

    private fun listCategoryNames(cat: CategoryResponse): List<String> {
        var catNameList= listOf<String>()
        for (catName in cat) {

        }
        return catNameList
    }

    private fun listenSearchBar() {
        binding.apply {
            categorySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(list.contains(query.toString().toLowerCase(Locale("TR-tr")))){ //BAŞINA ARAMA STRİNG ARRAY EKLE //TODO
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
