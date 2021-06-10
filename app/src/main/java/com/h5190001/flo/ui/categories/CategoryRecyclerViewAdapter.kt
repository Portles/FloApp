package com.h5190001.flo.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190001.flo.data.datasource.resources.Constants
import com.h5190001.flo.databinding.CategoryCardViewBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.models.CategoryResponseItem
import com.h5190001.flo.utils.GlideUtil.getImageFromUrl

class CategoryRecyclerViewAdapter (
    var categorys: List<CategoryResponseItem>,
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

    fun setData(newCategorys: List<CategoryResponseItem>) {
        categorys = newCategorys
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                binding.categoryText.text = categorys[position].categoryName
                val url: String = Constants.CATEGORY_IMG_URL + categorys[position].category_pic_loc
                binding.categoryImage.getImageFromUrl(url)
                categoryCardview.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}
