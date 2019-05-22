package com.ynov.kotlin.rickmorty.presentation.detail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.models.Character
import com.ynov.kotlin.rickmorty.presentation.RmApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel : ViewModel() {
    var characterLiveData: MutableLiveData<Character> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun retrieveCharacters(id: Long){
        RmApplication.app.dataRepository.retrieveCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                    errorLiveData.postValue(it)
                }
            )
    }
}