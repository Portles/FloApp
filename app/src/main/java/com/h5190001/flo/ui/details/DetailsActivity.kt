package com.h5190001.flo.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190001.flo.R
import com.h5190001.flo.utils.Constants
import com.h5190001.flo.databinding.ActivityDetailsBinding
import com.h5190001.flo.data.models.Item
import com.h5190001.flo.utils.GlideUtil.getImageFromUrl
import com.h5190001.flo.utils.ObjectUtil

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        initBinding()
        val item: Item = ObjectUtil.jsonStringToItemObje(intent.getStringExtra(applicationContext.getResources().getString(
            R.string.data))!!)
        initData(item)
    }

    private fun initBinding() {
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
}