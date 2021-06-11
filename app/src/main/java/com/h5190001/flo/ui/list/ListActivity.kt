package com.h5190001.flo.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190001.flo.R
import com.h5190001.flo.ui.details.DetailsActivity
import com.h5190001.flo.databinding.ActivityListBinding
import com.h5190001.flo.utils.ItemClickListener
import com.h5190001.flo.data.models.Item
import com.h5190001.flo.utils.*
import com.h5190001.flo.utils.AlertdialogUtil.BuildSortAlert
import com.h5190001.flo.utils.Constants.GRID_VIEW
import com.h5190001.flo.utils.Constants.LINEER_VIEW
import com.h5190001.flo.utils.ObjectUtil.jsonStringToObje
import com.h5190001.flo.utils.ProgressDialogUtil.DissmisDialog
import com.h5190001.flo.utils.ProgressDialogUtil.ShowDialog
import java.util.*
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    var rcyclerviewState = 0

    companion object { var list: List<Item>? = null
        lateinit var listAdapter: ListRecyclerViewAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        init()
    }

    private fun init() {
        initBinding()
        ShowDialog(this@ListActivity)
        list = jsonStringToObje(intent.getStringExtra(applicationContext.getResources().getString(R.string.list))!!)
        setRecyclerViewData()
        listenSortButton()
    }

    private fun initBinding() {
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun AlertDialogAction() {
        BuildSortAlert(this@ListActivity, list!! as ArrayList<Item>)
    }

    private fun setRecyclerViewData() {
        binding.apply {
            list.let {
                listAdapter = ListRecyclerViewAdapter(it!!, object : ItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@ListActivity, DetailsActivity::class.java)
                        val data: String = ObjectUtil.objectToString(it.get(position))
                        intent.putExtra(applicationContext.getResources().getString(R.string.data),data)
                        startActivity(intent)
                    }
                })
                binding.listRecyclerview.adapter = listAdapter
                listRecyclerview.layoutManager = GridLayoutManager(applicationContext,GRID_VIEW)
                DissmisDialog()
                listenChangeRecyclerViewButton()
            }
        }
    }

    private fun SwitchRecyclerViewLayout() {
        binding.apply {
            if (rcyclerviewState == 0) {
                listRecyclerview.layoutManager = GridLayoutManager(applicationContext, GRID_VIEW)
            } else {
                listRecyclerview.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
            }
        }
    }
                                                                                                        // TODO BURAYI ADAM ET
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