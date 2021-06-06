package com.h5190001.flo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190001.flo.R
import com.h5190001.flo.adapters.CategoryRecyclerViewAdapter
import com.h5190001.flo.adapters.ListRecyclerViewAdapter
import com.h5190001.flo.databinding.ActivityCategoryBinding
import com.h5190001.flo.databinding.ActivityListBinding
import com.h5190001.flo.databinding.ActivityLoginBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.models.Item
import com.h5190001.flo.utils.AlertboxUtil
import com.h5190001.flo.utils.AlertdialogUtil
import com.h5190001.flo.utils.MESSAGE_TYPE
import com.h5190001.flo.utils.ObjectUtil.jsonStringToObject
import com.h5190001.flo.utils.ToastUtil
import java.util.*

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var listAdapter: ListRecyclerViewAdapter

    var list: ArrayList<String>? = null
    var listArray= arrayListOf("Ayakkabı 1", "Ayakkabı 2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setBindings()
        val items: ArrayList<Item> = jsonStringToObject(intent.getStringExtra("list")!!)
        setRecyclerViewData(items)
    }

    private fun setBindings() {
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView
        binding.listRecyclerview
    }

    private fun AlertDialogAction() {
        AlertdialogUtil.BuildSortAlert(applicationContext)
    }

    private fun setRecyclerViewData(Items: ArrayList<Item>) {
        binding.apply {
            listAdapter = ListRecyclerViewAdapter(Items, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    //Toast.makeText(applicationContext,categoryList.get(position),Toast.LENGTH_SHORT).show()
                }
            })
            binding.listRecyclerview.adapter = listAdapter
            listRecyclerview.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun listenSearchBar() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(list!!.contains(query.toString().toLowerCase(Locale("TR-tr")))){
                        //categoryAdapter.filter(query);
                    }else{
                        ToastUtil.BuildToast(applicationContext, query!!.toString() , MESSAGE_TYPE.SHORT_MESSAGE) //DİKKAT ÇÖKERTEBİLİR
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