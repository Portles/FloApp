package com.h5190001.flo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190001.flo.databinding.CategoryCardViewBinding
import com.h5190001.flo.interfaces.ItemClickListener

class CategoryRecyclerViewAdapter (
    var categorys: ArrayList<String>,
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
                binding.categoryText.text = categorys[position]
                CategoryCardview.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
                CategoryCardview.setOnClickListener {
                    onItemClickListener.onDelete(position)
                }
            }
        }
    }
}
