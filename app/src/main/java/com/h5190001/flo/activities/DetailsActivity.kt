package com.h5190001.flo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.h5190001.flo.data.datasource.resources.Constants
import com.h5190001.flo.databinding.ActivityDetailsBinding
import com.h5190001.flo.models.Item
import com.h5190001.flo.utils.ObjectUtil

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setBindings()
        val item: Item = ObjectUtil.jsonStringToItemObje(intent.getStringExtra("object")!!)
        initData(item)
    }

    private fun setBindings() {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initData(item: Item) {
        val url: String = Constants.CATEGORY_IMG_URL + item.pic_loc

        binding.apply {
            insideMaterialText.text = item.details?.insideMaterial
            outsideMaterialText.text = item.details?.outsideMaterial
            soleText.text = item.details?.sole

            brandText.text = item.specs?.brand
            genderText.text = item.specs?.gender
            skuText.text = item.specs?.sku
            modalText.text = item.specs?.modal
            colorText.text = item.specs?.color
            sayaText.text = item.specs?.saya

            imageView.getImageFromUrl(url)
        }
    }

    fun ImageView.getImageFromUrl(url: String){
        Glide.with(this.context).load(url).into(this)
    }
    //TODO UTILE CEVİR
}