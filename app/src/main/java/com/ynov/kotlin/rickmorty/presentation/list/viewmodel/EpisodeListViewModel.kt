package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.models.Character
import com.ynov.kotlin.rickmorty.data.entity.models.Episode
import com.ynov.kotlin.rickmorty.presentation.RmApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EpisodeListViewModel : ViewModel() {

    var episodesLiveData: MutableLiveData<List<Episode>> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        retrieveCharacters()
    }

    private fun retrieveCharacters(){
        RmApplication.app.dataRepository.retrieveEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    episodesLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                    errorLiveData.postValue(it)
                }
            )
    }
}