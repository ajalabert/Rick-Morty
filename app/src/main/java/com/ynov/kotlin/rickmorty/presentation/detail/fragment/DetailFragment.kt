package com.ynov.kotlin.rickmorty.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment(var characterId: Int) : Fragment() {
    private lateinit var viewModel: CharacterDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)

        viewModel.characterLiveData.observe(this, Observer {
            view_character_detail_name.text = it.name
            Picasso.get().load(it.image).into(view_character_detail_image)
            view_character_detail_gender.text = "Gender: ${it.gender}"
            view_character_detail_location.text = "Location: ${it.location}"
            view_character_detail_origin.text = "Origin: ${it.origin}"
            view_character_detail_specie.text = "Species: ${it.species}"
            view_character_detail_status.text = "Status: ${it.status}"
        })

        viewModel.errorLiveData.observe(this, Observer {
            getView()?.let { view -> Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show() }
        })

        viewModel.retrieveCharacters(characterId)
    }
}
