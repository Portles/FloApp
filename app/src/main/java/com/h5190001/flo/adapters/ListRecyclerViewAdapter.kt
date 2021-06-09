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
import com.h5190001.flo.utils.GlideUtil.getImageFromUrl

class ListRecyclerViewAdapter (
    var list: List<Item>,
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
                    binding.listText.text = list[position].name
                    val url: String = Constants.CATEGORY_IMG_URL + list[position].pic_loc
                    binding.listImage.getImageFromUrl(url)
                    listCardview.setOnClickListener {
                        onItemClickListener.onItemClick(position)
                    }
                }
            }
        }
}