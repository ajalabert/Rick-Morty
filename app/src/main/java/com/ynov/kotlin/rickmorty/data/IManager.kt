package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import io.reactivex.Single

interface IManager {
    fun retrieveCharacters(): Single<List<Character>>
    fun retrieveCharacter(id: Long): Single<Character>
}