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
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.CharacterListViewModel
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter.CharacterListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(){

    private var loading = false
    private var pageNumber = 1

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListAdapter = CharacterListAdapter()
        fragment_list_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_list_recyclerview.adapter = characterListAdapter

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

        characterListAdapter.onItemClick = {
            startActivity(context?.let { context -> DetailActivity.newIntent(context, it.id) })
        }

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        viewModel.charactersLiveData.observe(this, Observer {
            characterListAdapter.updateList(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            getView()?.let { view -> Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show() }
        })

        viewModel.retrieveCharacters(pageNumber)
    }
}