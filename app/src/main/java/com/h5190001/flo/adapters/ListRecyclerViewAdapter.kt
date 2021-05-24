package com.h5190001.flo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190001.flo.databinding.ListCardViewBinding
import com.h5190001.flo.interfaces.ItemClickListener

class ListRecyclerViewAdapter (
    var list: ArrayList<String>,
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
                    binding.categoryText.text = list[position]
                    ListCardView.setOnClickListener {
                        onItemClickListener.onItemClick(position)
                    }
                    ListCardView.setOnClickListener {
                        onItemClickListener.onDelete(position)
                    }
                }
            }
        }
}