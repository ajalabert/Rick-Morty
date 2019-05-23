package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode
import io.reactivex.Single

interface IManager {
    fun retrieveCharacters(page: Int): Single<List<Character>>
    fun retrieveCharacter(id: Long): Single<Character>
    fun retrieveEpisodes(page: Int): Single<List<Episode>>
}