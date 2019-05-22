package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode
import io.reactivex.Single

interface IManager {
    fun retrieveCharacters(): Single<List<Character>>
    fun retrieveCharacter(id: Long): Single<Character>
    fun retrieveEpisodes(): Single<List<Episode>>
}