 package com.example.onlinesavdo.screen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinesavdo.Model.CategoryModel
import com.example.onlinesavdo.Model.OfferModel
import com.example.onlinesavdo.Model.ProductModel
import com.example.onlinesavdo.api.repository.ShopRepository
import com.example.onlinesavdo.db.AppDatabese
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

 class   MainViewModel: ViewModel() {

    val repository = ShopRepository()
    val error = MutableLiveData<String>()
    val progress = MutableLiveData<Boolean>()
    val offersData = MutableLiveData<List<OfferModel>>()
    val categoriesData = MutableLiveData<List<CategoryModel>>()
    val productsData = MutableLiveData<List<ProductModel>>()

    fun getOffers(){

        repository.getOffers(error, progress,  offersData)

    }
    fun getCategories(){
        repository.getCategories(error, categoriesData)
    }
    fun getTopProducts(){
        repository.getTopProducts(error, productsData)
    }
    fun getProductsByCategory(id: Int){
        repository.getProductsByCategory(id, error, productsData)
    }
    fun getProductByIds(ids: List<Int>){
        repository.getProductsByIds(ids, error, progress,  productsData)
    }
     fun insertAllProducts2DB(items: List<ProductModel>){
         CoroutineScope(Dispatchers.IO).launch{
             AppDatabese.getDatabase().getProductDao().deleteAll()
             AppDatabese.getDatabase().getProductDao().insertAll(items)
         }
     }
     fun insertAllCategories2DB(items: List<CategoryModel>){
         CoroutineScope(Dispatchers.IO).launch{
             AppDatabese.getDatabase().getCategoryDao().deleteAll()
            AppDatabese.getDatabase().getCategoryDao().insertAll(items)
         }
     }
     fun getAllDBProducts(){
         CoroutineScope(Dispatchers.Main).launch {
             productsData.value = withContext(Dispatchers.IO){AppDatabese.getDatabase().getProductDao().getAllProducts()}
         }
     }
     fun getAllDBCategories(){
         CoroutineScope(Dispatchers.Main).launch {
             categoriesData.value = withContext(Dispatchers.IO){AppDatabese.getDatabase().getCategoryDao().getAllCategories()}
         }
     }
}