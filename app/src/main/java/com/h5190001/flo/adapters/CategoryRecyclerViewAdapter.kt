package com.h5190001.flo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.h5190001.flo.activities.CategoryActivity
import com.h5190001.flo.data.datasource.resources.Constants
import com.h5190001.flo.databinding.CategoryCardViewBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.utils.GlideUtil.downloadPicAndShow
import java.util.*

class CategoryRecyclerViewAdapter (
    var categorys: CategoryResponse,
    var onItemClickListener: ItemClickListener ) :RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CategoryCardViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categorys.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                binding.categoryText.text = categorys[position].categoryName
                val url: String = Constants.CATEGORY_IMG_URL + categorys[position].category_pic_loc
                binding.categoryImage.getImageFromUrl(url)
                CategoryCardview.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    fun ImageView.getImageFromUrl(url: String){
        Glide.with(this.context).load(url).into(this)
    }
    //TODO UTILE CEVİR
}
