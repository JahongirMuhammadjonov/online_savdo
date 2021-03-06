package com.example.onlinesavdo.api.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlinesavdo.Model.BaseResponse
import com.example.onlinesavdo.Model.CategoryModel
import com.example.onlinesavdo.Model.OfferModel
import com.example.onlinesavdo.Model.ProductModel
import com.example.onlinesavdo.Model.request.GetProductsByIdsRequest
import com.example.onlinesavdo.api.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ShopRepository {

    val compositeDisposable = CompositeDisposable()
    fun getOffers(
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        success: MutableLiveData<List<OfferModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                   .subscribeWith(object : DisposableObserver<BaseResponse<List<OfferModel>>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<OfferModel>>) {
                        progress.value = false
                        if (t.success) {
                            success.value = t.data
                         } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value = false
                        error.value = e.localizedMessage
                    }
                })

        )


    }

    fun getCategories(
        error: MutableLiveData<String>,
        success: MutableLiveData<List<CategoryModel>>
    ) {

        compositeDisposable.add(
            NetworkManager.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<CategoryModel>>) {

                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {

                        error.value = e.localizedMessage
                    }
                })

        )

    }

    fun getTopProducts(
        error: MutableLiveData<String>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getTopProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<ProductModel>>) {

                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {

                          error.value = e.localizedMessage
                    }
                })
        )
    }

    fun getProductsByCategory(
        id: Int,
        error: MutableLiveData<String>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getCategoryProducts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<ProductModel>>) {

                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {

                        error.value = e.localizedMessage
                    }
                })
        )
    }
    fun getProductsByIds (ids: List<Int>, error: MutableLiveData<String>, progress: MutableLiveData<Boolean>, success: MutableLiveData<List<ProductModel>>) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getProductsByIds(GetProductsByIdsRequest(ids))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        progress.value  =  false
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }
                         override fun onError(e: Throwable) {
                        progress.value = false

                        error.value = e.localizedMessage
                    }
                })
        )
    }
}