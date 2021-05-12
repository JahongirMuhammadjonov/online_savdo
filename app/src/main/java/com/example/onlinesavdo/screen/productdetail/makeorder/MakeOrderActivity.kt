package com.example.onlinesavdo.screen.productdetail.makeorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinesavdo.R
import com.example.onlinesavdo.screen.MapsActivity
import com.example.onlinesavdo.Model.AddressModel
import com.example.onlinesavdo.Model.ProductModel
import com.example.onlinesavdo.utiles.Constants
import kotlinx.android.synthetic.main.activity_make_order.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {
    var address: AddressModel? = null
    lateinit var items: List<ProductModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)

        items = intent.getSerializableExtra(Constants.EXTRA_DATA) as List<ProductModel>
        if (!EventBus.getDefault().isRegistered(this)){
              EventBus.getDefault().register(this)
        }
        tvTotalAmount.setText(items.sumByDouble { it.cartCount.toDouble() * (it.price.replace(" ", "").toDoubleOrNull() ?: 0.0)}.toString())
        edAddress.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
    }
}
    @Subscribe
    fun onEvent(address: AddressModel){
        this.address = address
        edAddress.setText("${address.latituda}, ${address.longituda}")
    }
}