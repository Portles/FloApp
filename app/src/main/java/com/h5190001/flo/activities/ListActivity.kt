package com.h5190001.flo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190001.flo.adapters.ListRecyclerViewAdapter
import com.h5190001.flo.databinding.ActivityListBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.models.Item
import com.h5190001.flo.utils.*
import com.h5190001.flo.utils.AlertdialogUtil.BuildSortAlert
import com.h5190001.flo.utils.ObjectUtil.jsonStringToObje
import com.h5190001.flo.utils.ProgressDialogUtil.DissmisDialog
import com.h5190001.flo.utils.ProgressDialogUtil.ShowDialog
import java.util.*

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var listAdapter: ListRecyclerViewAdapter

    var rcyclerviewState = 0

    var list: ArrayList<String>? = null
    var listArray= arrayListOf("Ayakkabı 1", "Ayakkabı 2") //TODO SEARCH KISMI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        init()
    }

    private fun init() {
        setBindings()
        ShowDialog(this@ListActivity)
        val items: List<Item> = jsonStringToObje(intent.getStringExtra("list")!!)
        setRecyclerViewData(items)
        listenSortButton()
    }

    private fun setBindings() {
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun AlertDialogAction() {
        BuildSortAlert(this@ListActivity)
    }

    private fun setRecyclerViewData(Items: List<Item>) {
        binding.apply {
            listAdapter = ListRecyclerViewAdapter(Items, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    //Toast.makeText(applicationContext,categoryList.get(position),Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ListActivity,DetailsActivity::class.java)
                    val data: String = ObjectUtil.objectToString(Items[position])
                    intent.putExtra("object",data) //TODO TIRNAKLARI DUZELT
                    startActivity(intent)
                }
            })
            binding.listRecyclerview.adapter = listAdapter
            listRecyclerview.layoutManager = GridLayoutManager(applicationContext,2)
            DissmisDialog()
            listenChangeRecyclerViewButton()
        }
    }

    private fun SwitchRecyclerViewLayout() {
        binding.apply {
            if (rcyclerviewState == 0) {
                listRecyclerview.layoutManager = GridLayoutManager(applicationContext, 2)
            } else {
                listRecyclerview.layoutManager = GridLayoutManager(applicationContext, 1)
            }
        }
    }

    private fun listenChangeRecyclerViewButton() {
        binding.apply {
            changeRecyclerviewButton.setOnClickListener {
                if (rcyclerviewState == 0) {
                    rcyclerviewState = 1
                } else {
                    rcyclerviewState = 0
                }
                SwitchRecyclerViewLayout()
            }
        }
    }

    //SEARCH PLACE
    private fun listenSortButton() {
        binding.apply {
            sortButton.setOnClickListener() {
                AlertDialogAction()
            }
        }
    }
}