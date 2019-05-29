package com.ynov.kotlin.rickmorty.presentation.list.fragment

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
import com.ynov.kotlin.rickmorty.presentation.list.adapter.EpisodeListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.listener.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_list.*

class EpisodesFragment : Fragment() {

    private lateinit var episodeListAdapter: EpisodeListAdapter
    private lateinit var viewModel: EpisodeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = LinearLayoutManager(context)

        episodeListAdapter = EpisodeListAdapter()
        fragment_list_recyclerview.layoutManager = linearLayout
        fragment_list_recyclerview.adapter = episodeListAdapter

        val scrollListener = EndlessRecyclerViewScrollListener(linearLayout)
        scrollListener.onLoadMore = { pageNumber: Int, _: Int, _: RecyclerView ->
            viewModel.retrieveEpisodes(pageNumber)
        }

        fragment_list_recyclerview.addOnScrollListener(scrollListener)

        viewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)

        viewModel.episodesLiveData.observe(this, Observer {
            episodeListAdapter.updateList(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            getView()?.showSnackBar(it.message.toString())
        })


        // TODO vous auriez pu mettre cet appel dans le init {} du viewmodel, ça permet de lancer l'appel au repository
        //  pendant que le fragment se construit en asynchrone
        viewModel.retrieveEpisodes(1)
    }
}