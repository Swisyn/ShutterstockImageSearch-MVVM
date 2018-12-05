package com.cuneytayyildiz.shutterstockassignment.data.repository

import androidx.lifecycle.MutableLiveData
import com.cuneytayyildiz.shutterstockassignment.data.api.ShutterstockService
import com.cuneytayyildiz.shutterstockassignment.data.model.Resource
import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShutterstockRepository @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val api: ShutterstockService
) : ShutterstockRepositoryImpl {
    val data = MutableLiveData<Resource<SearchResultModel>>()

    override fun doSearch(query: String, page: Int): MutableLiveData<Resource<SearchResultModel>> {
        compositeDisposable.add(
            api.search(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { data.value = Resource.loading() }
                .doOnError { data.value = Resource.error(it) }
                .subscribe {
                    if (it.isSuccessful) {
                        data.value = Resource.success(it.body())
                    } else {
                        data.value = Resource.error(Throwable(it.errorBody().toString()))
                    }
                }
        )

        return data
    }

    override fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}