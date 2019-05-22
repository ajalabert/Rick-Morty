package com.ynov.kotlin.rickmorty.data
import io.reactivex.Single
import com.ynov.kotlin.rickmorty.data.entity.models.Character

class DataRepository(private val apiManager: ApiManager) {
    fun retrieveCharacters(): Single<List<Character>> = apiManager.retrieveCharacters().map{
        it.map{character -> character.toModel() }
    }

    fun retrieveCharacter(id: Long): Single<Character> = apiManager.retrieveCharacter(id).map {
        it.toModel()
    }
}