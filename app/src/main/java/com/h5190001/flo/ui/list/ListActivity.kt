package com.h5190001.flo.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190001.flo.R
import com.h5190001.flo.ui.details.DetailsActivity
import com.h5190001.flo.databinding.ActivityListBinding
import com.h5190001.flo.utils.interfaces.ItemClickListener
import com.h5190001.flo.data.models.Item
import com.h5190001.flo.utils.*
import com.h5190001.flo.utils.AlertdialogUtil.BuildSortAlert
import com.h5190001.flo.utils.Constants.GRID_LAYOUT
import com.h5190001.flo.utils.ObjectUtil.jsonStringToObje
import com.h5190001.flo.utils.ProgressDialogUtil.DissmisDialog
import com.h5190001.flo.utils.ProgressDialogUtil.ShowProgressDialog
import java.util.*
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    companion object { var list: List<Item>? = null
        lateinit var listAdapter: ListRecyclerViewAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        init()
    }

    private fun init() {
        initBinding()
        ShowProgressDialog(this@ListActivity)
        list = jsonStringToObje(intent.getStringExtra(applicationContext.getResources().getString(R.string.list))!!)
        initRecyclerViewData()
        ListenSortButton()
    }

    private fun initBinding() {
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun AlertDialogAction() {
        BuildSortAlert(applicationContext,this@ListActivity, list!! as ArrayList<Item>)
    }

    private fun initRecyclerViewData() {
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
                listRecyclerview.layoutManager = GridLayoutManager(applicationContext,GRID_LAYOUT)
                DissmisDialog()
                listenChangeRecyclerViewSwitch()
            }
        }
    }

    private fun listenChangeRecyclerViewSwitch() {
        binding.apply {
            changeRcyclerViewSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    listRecyclerview.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                    recyclerViewStateText.text = applicationContext.getString(R.string.list_recyclerview_state_vertical)
                }else {
                    listRecyclerview.layoutManager = GridLayoutManager(applicationContext, GRID_LAYOUT)
                    recyclerViewStateText.text = applicationContext.getString(R.string.list_recyclerview_state_grid)
                }
            }
        }
    }

    private fun ListenSortButton() {
        binding.apply {
            sortButton.setOnClickListener() {
                AlertDialogAction()
            }
        }
    }
}