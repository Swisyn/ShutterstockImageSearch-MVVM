package com.cuneytayyildiz.shutterstockassignment.data.api

import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShutterstockService {
    @GET("images/search?image_type=photo")
    fun search(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int = 15): Observable<Response<SearchResultModel>>
}