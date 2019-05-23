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
import com.ynov.kotlin.rickmorty.presentation.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.CharacterListViewModel
import com.ynov.kotlin.rickmorty.presentation.list.adapter.CharacterListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.listener.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_list.*

class CharactersFragment : Fragment(){

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = LinearLayoutManager(context)

        characterListAdapter = CharacterListAdapter()
        fragment_list_recyclerview.layoutManager = linearLayout
        fragment_list_recyclerview.adapter = characterListAdapter

        val scrollListener = EndlessRecyclerViewScrollListener(linearLayout)
        scrollListener.onLoadMore = { pageNumber: Int, _: Int, _: RecyclerView ->
            viewModel.retrieveCharacters(pageNumber)
        }

        fragment_list_recyclerview.addOnScrollListener(scrollListener)

        characterListAdapter.onItemClick = {
            startActivity(context?.let { context -> DetailActivity.newIntent(context, it.id) })
        }

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        viewModel.charactersLiveData.observe(this, Observer {
            characterListAdapter.updateList(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            getView()?.showSnackBar(it.message.toString())
        })

        viewModel.retrieveCharacters(1)
    }
}