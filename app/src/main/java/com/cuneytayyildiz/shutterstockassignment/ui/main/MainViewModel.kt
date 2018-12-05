package com.cuneytayyildiz.shutterstockassignment.ui.main

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.cuneytayyildiz.shutterstockassignment.data.model.Resource
import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel
import com.cuneytayyildiz.shutterstockassignment.data.repository.ShutterstockRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel : ViewModel() {

    private val searchInput: MutableLiveData<String> = MutableLiveData()
    private var currentPage: Int = 1

    private lateinit var repository: ShutterstockRepository

    @Inject
    fun init(repository: ShutterstockRepository) {
        this.repository = repository
    }

    val searchResult: LiveData<Resource<SearchResultModel>> = switchMap(searchInput) {
        if (it.isNotEmpty()) {
            repository.doSearch(it, currentPage)
        } else {
            MutableLiveData()
        }
    }

    fun search(query: String, page: Int = 1) {
        this.currentPage = page
        searchInput.value = query
    }

    override fun onCleared() {
        repository.clearCompositeDisposable()
        super.onCleared()
    }

    companion object {
        fun create(activity: FragmentActivity): MainViewModel {
            return ViewModelProviders.of(activity).get(MainViewModel::class.java)
        }
    }
}
