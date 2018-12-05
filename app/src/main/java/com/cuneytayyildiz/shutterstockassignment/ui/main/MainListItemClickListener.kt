package com.cuneytayyildiz.shutterstockassignment.ui.main

import android.view.View
import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel

interface MainListItemClickListener {
    fun onPhotoClick(item: SearchResultModel.DataEntity, view: View)
}