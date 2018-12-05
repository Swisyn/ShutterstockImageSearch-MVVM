package com.cuneytayyildiz.shutterstockassignment.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.cuneytayyildiz.shutterstockassignment.R
import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel
import com.cuneytayyildiz.shutterstockassignment.utils.extensions.load

class MainListAdapter(
    private val context: Context,
    var items: MutableList<SearchResultModel.DataEntity>,
    private val clickListener: MainListItemClickListener
) :
    RecyclerView.Adapter<MainListAdapter.ListItemViewHolder>() {

    private val set = ConstraintSet()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = items[position]

        item.assets?.largeThumb?.let {
            holder.imagePreview.load(it.url)

            with(set) {
                clone(holder.constraintLayout)
                setDimensionRatio(holder.imagePreview.id, "${it.width}:${it.height}")
                applyTo(holder.constraintLayout)
            }
        }

        holder.itemView.setOnClickListener { clickListener.onPhotoClick(item, holder.itemView) }
    }

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.parentContsraint)
        var imagePreview: ImageView = itemView.findViewById(R.id.image_preview)
    }
}