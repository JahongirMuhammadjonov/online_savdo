 package com.example.onlinesavdo.screen.productdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.onlinesavdo.R
import com.example.onlinesavdo.Model.ProductModel
import com.example.onlinesavdo.utiles.Constants
import com.example.onlinesavdo.utiles.PrefUtils
import kotlinx.android.synthetic.main.activity_product_detail.*

 class      ProductDetailActivity : AppCompatActivity() {
    lateinit var item: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        cardViewBack.setOnClickListener{
            finish()
        }
        cardViewFavorite.setOnClickListener{
            PrefUtils.setFvorite(item)

            if(PrefUtils.checkFavorite(item)){
                imgFavorite.setImageResource(R.drawable.heart2 )
            }else{
                imgFavorite.setImageResource(R.drawable.ic_heart)
            }
        }
        item = intent.getSerializableExtra(Constants.EXTRA_DATA) as ProductModel

        tvTitle.text = item.name
        tvProductName.text = item.name
        tvProductPrice.text = item.price

        if (PrefUtils.getCartCount(item) > 0){
            btnAdd2Cart.visibility = View.GONE
            }
        if(PrefUtils.checkFavorite(item)){
            imgFavorite.setImageResource(R.drawable.heart2 )
        }else{
            imgFavorite.setImageResource(R.drawable.ic_heart)
        }

        Glide.with(this).load(Constants.HOST_IMAGE + item.image).into(imgProduct)

        btnAdd2Cart.setOnClickListener{
            item.cartCount = 1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Product added to cart!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}