package com.example.onlinesavdo.screen.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinesavdo.R
import com.example.onlinesavdo.Model.ProductModel
import com.example.onlinesavdo.screen.productdetail.ProductDetailActivity
import com.example.onlinesavdo.utiles.Constants
import kotlinx.android.synthetic.main.product_item_layout.view.*

class  ProductAdapter (val items: List<ProductModel>): RecyclerView.Adapter<ProductAdapter.ItemHolder>()  {
    class ItemHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false ))
    }

    override fun getItemCount(): Int {
       return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, ProductDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA, item)
            it.context.startActivity(intent)
        }
        Glide.with(holder.itemView.context).load(Constants.HOST_IMAGE + item.image).into(holder.itemView.imgProduct)
        holder.itemView.tvName.text = item.name
        holder.itemView.tvPrice.text = item.price
    }

}