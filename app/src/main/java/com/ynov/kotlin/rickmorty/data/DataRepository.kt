package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.models.Character
import io.reactivex.Single


class DataRepository(private val apiManager: ApiManager) {
    fun retrieveCharacters(): Single<List<Character>> = apiManager.retrieveCharacters().map{
        it.map{character -> Character(character.name) }
    }}
