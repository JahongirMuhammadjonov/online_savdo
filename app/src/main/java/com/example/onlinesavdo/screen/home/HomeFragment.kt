package com.example.onlinesavdo.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinesavdo.R
import com.example.onlinesavdo.screen.MainViewModel
import com.example.onlinesavdo.Model.CategoryModel
import com.example.onlinesavdo.Model.OfferModel
import com.example.onlinesavdo.utiles.Constants
import com.example.onlinesavdo.screen.view.CategoryAdapter
import com.example.onlinesavdo.screen.view.CategoryAdapterCallback
import com.example.onlinesavdo.screen.view.ProductAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class   HomeFragment : Fragment() {
    var offers: List<OfferModel> = emptyList()
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
     ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe.setOnRefreshListener{
            loadData()
        }
        recyclerCategories.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerProducts.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.progress.observe(requireActivity(), Observer {
            swipe.isRefreshing = it
        })
        viewModel.offersData.observe(requireActivity(), Observer {
 
            carouselView.setImageListener { position, imageView ->
                Glide.with(imageView).load(Constants.HOST_IMAGE + it[position].image).into(imageView)
            }
            carouselView.pageCount = it.count()
        })
        viewModel.categoriesData.observe(requireActivity(), Observer {
            recyclerCategories.adapter = CategoryAdapter(it, object: CategoryAdapterCallback{
                override fun onClickItem(item: CategoryModel) {
                    viewModel.getProductsByCategory(item.id)
                }
            })
        })
        viewModel.productsData.observe(requireActivity(), Observer {
            recyclerProducts.adapter = ProductAdapter(it)


        })
        loadData()
    }

    fun loadData() {
        viewModel.getOffers()
        viewModel.getAllDBCategories()
        viewModel.getAllDBProducts()
//         viewModel.getTopProducts()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}