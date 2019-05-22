package com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.EpisodeListViewModel
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter.EpisodeListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class EpisodesFragment : Fragment() {
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

        episodeListAdapter.onItemClick = {
            startActivity(context?.let { context -> DetailActivity.newIntent(context, it.id) })
        }

        viewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)

        viewModel.episodesLiveData.observe(this, Observer {
            episodeListAdapter.updateList(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            getView()?.let { view -> Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show() }
        })
    }
}