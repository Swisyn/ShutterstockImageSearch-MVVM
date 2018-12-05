package com.cuneytayyildiz.shutterstockassignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cuneytayyildiz.shutterstockassignment.R
import com.cuneytayyildiz.shutterstockassignment.ShutterstockApp
import com.cuneytayyildiz.shutterstockassignment.data.model.Resource
import com.cuneytayyildiz.shutterstockassignment.data.model.SearchResultModel
import com.cuneytayyildiz.shutterstockassignment.ui.preview.PreviewActivity
import com.cuneytayyildiz.shutterstockassignment.utils.EndlessRecyclerViewScrollListener
import com.cuneytayyildiz.shutterstockassignment.utils.extensions.isConnectedToInternet
import com.cuneytayyildiz.shutterstockassignment.utils.extensions.onDoneAction
import com.cuneytayyildiz.shutterstockassignment.utils.extensions.snack

class MainFragment : Fragment(), MainListItemClickListener {

    //<editor-fold desc="Views">
    private lateinit var coordinatorContainer: CoordinatorLayout
    private lateinit var editTextQuery: EditText
    private lateinit var swipeRefreshContainer: SwipeRefreshLayout
    private lateinit var recyclerViewImages: RecyclerView
    //</editor-fold>

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainListAdapter: MainListAdapter
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        initViews(rootView)

        setupRecyclerView()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { activity ->
            mainViewModel = MainViewModel.create(activity)
            ShutterstockApp.appComponent.inject(mainViewModel)

            mainViewModel.searchResult?.observe(viewLifecycleOwner, Observer {
                when (it?.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                    }
                    Resource.Status.SUCCESS -> {
                        hideLoading()

                        renderList(it)
                    }
                    Resource.Status.ERROR -> {
                        coordinatorContainer.snack(
                                it.exception?.message
                                        ?: activity.getString(R.string.error_message_unknown)
                        ) { snackbar ->
                            if (!editTextQuery.text.isEmpty()) {
                                mainViewModel.search(editTextQuery.text.toString(), 1)
                            }
                            snackbar.dismiss()
                        }
                    }
                }
            })
        }

    }

    private fun clearPreviousSearchResults() {
        if (::mainListAdapter.isInitialized) {
            mainListAdapter.items.clear()
            endlessRecyclerViewScrollListener.resetState()
        }
    }

    private fun initViews(rootView: View) {
        coordinatorContainer = rootView.findViewById(R.id.coordinator_container)
        editTextQuery = rootView.findViewById(R.id.edit_text_query)
        swipeRefreshContainer = rootView.findViewById(R.id.swipe_refresh_container)
        recyclerViewImages = rootView.findViewById(R.id.recycler_view_images)

        swipeRefreshContainer.isEnabled = false

        editTextQuery.onDoneAction {
            if (::mainViewModel.isInitialized) {
                clearPreviousSearchResults()

                doSearch(it, 1)
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerViewImages.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            endlessRecyclerViewScrollListener = object :
                    EndlessRecyclerViewScrollListener(recyclerViewImages.layoutManager as StaggeredGridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    doSearch(editTextQuery.text.toString(), page)
                }
            }

            addOnScrollListener(endlessRecyclerViewScrollListener)
        }
    }

    private fun doSearch(query: String, page: Int) {
        if (isConnectedToInternet()) {
            if (!query.isEmpty()) {
                mainViewModel.search(query, page)
            }
        } else {
            activity?.getString(R.string.error_no_internet)?.let { activity ->
                coordinatorContainer.snack(activity) {
                    doSearch(query, page)

                    it.dismiss()
                }
            }
        }
    }

    private fun renderList(it: Resource<SearchResultModel>) {
        activity?.let { activity ->
            it.data?.let { data ->
                if (!::mainListAdapter.isInitialized) {
                    mainListAdapter = MainListAdapter(activity, data.data, this)
                    recyclerViewImages.adapter = mainListAdapter
                } else {
                    mainListAdapter.items.addAll(data.data)
                    mainListAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun showLoading() {
        if (!swipeRefreshContainer.isRefreshing) {
            swipeRefreshContainer.post { swipeRefreshContainer.isRefreshing = true }
        }
    }

    private fun hideLoading() {
        if (swipeRefreshContainer.isRefreshing) {
            swipeRefreshContainer.post { swipeRefreshContainer.isRefreshing = false }
        }
    }

    override fun onPhotoClick(item: SearchResultModel.DataEntity, view: View) {
        activity?.let {
            val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(it, view, it.getString(R.string.transition_preview))
            startActivity(PreviewActivity.createIntent(it, item.assets?.largeThumb?.url), options.toBundle())
        }
    }
}