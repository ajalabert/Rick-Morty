package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.models.Character
import com.ynov.kotlin.rickmorty.presentation.RmApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {

    var charactersLiveData: MutableLiveData<List<Character>> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun retrieveCharacters(page: Int){
        compositeDisposable.add(
        RmApplication.app.dataRepository.retrieveCharacters(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    charactersLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                    errorLiveData.postValue(it)
                }
            )
        )
    }
}