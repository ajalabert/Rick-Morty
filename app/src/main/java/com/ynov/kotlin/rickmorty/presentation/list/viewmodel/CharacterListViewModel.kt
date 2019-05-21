package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.models.Character
import com.ynov.kotlin.rickmorty.presentation.RmApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {

    var charactersLiveData: MutableLiveData<List<Character>> = MutableLiveData()

    init {
        retrieveCharacters()
    }

    private fun retrieveCharacters(){
        RmApplication.app.dataRepository.retrieveCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    charactersLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it);
                }
            )
    }
}