package com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.CharacterListViewModel
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter.CharacterListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(){

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_list_recyclerview

        characterListAdapter = CharacterListAdapter()
        fragment_list_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_list_recyclerview.adapter = characterListAdapter

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        viewModel.charactersLiveData.observe(this, Observer {
            characterListAdapter.updateList(it)
        })
    }
}