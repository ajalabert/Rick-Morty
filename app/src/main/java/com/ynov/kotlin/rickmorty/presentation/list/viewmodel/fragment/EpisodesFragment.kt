package com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.extension.showSnackBar
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.EpisodeListViewModel
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter.EpisodeListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class EpisodesFragment : Fragment() {

    private var loading = false
    private var pageNumber = 1

    private lateinit var episodeListAdapter: EpisodeListAdapter
    private lateinit var viewModel: EpisodeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodeListAdapter = EpisodeListAdapter()
        fragment_list_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_list_recyclerview.adapter = episodeListAdapter

        fragment_list_recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if(!loading && linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2){
                    loading = true
                    pageNumber++
                    viewModel.retrieveCharacters(pageNumber)
                    loading = false
                }
            }
        })

        viewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)

        viewModel.episodesLiveData.observe(this, Observer {
            episodeListAdapter.updateList(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            getView()?.showSnackBar(it.message.toString())
        })

        viewModel.retrieveCharacters(pageNumber)
    }
}