package com.h5190001.flo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.h5190001.flo.data.datasource.resources.Constants
import com.h5190001.flo.databinding.ListCardViewBinding
import com.h5190001.flo.interfaces.ItemClickListener
import com.h5190001.flo.models.Item

class ListRecyclerViewAdapter (
    var list: ArrayList<Item>,
    var onItemClickListener: ItemClickListener ) :RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: ListCardViewBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding =
                ListCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder) {
                binding.apply {
                    binding.categoryText.text = list[position].name
                    val url: String = Constants.CATEGORY_IMG_URL + list[position].pic_loc
                    binding.categoryImage.getImageFromUrl(url)
                    ListCardView.setOnClickListener {
                        onItemClickListener.onItemClick(position)
                    }
                }
            }
        }
    fun ImageView.getImageFromUrl(url: String){
        Glide.with(this.context).load(url).into(this)
    }
}