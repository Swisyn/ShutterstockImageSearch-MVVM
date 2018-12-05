package com.cuneytayyildiz.shutterstockassignment.data.repository

import androidx.lifecycle.MutableLiveData
import com.cuneytayyildiz.shutterstockassignment.data.model.Resource
import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel


interface ShutterstockRepositoryImpl {
    fun doSearch(query: String, page: Int):MutableLiveData<Resource<SearchResultModel>>
    fun clearCompositeDisposable()
}