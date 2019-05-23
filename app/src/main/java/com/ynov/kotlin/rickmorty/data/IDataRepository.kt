package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.models.Character
import com.ynov.kotlin.rickmorty.data.entity.models.Episode
import io.reactivex.Single

interface IDataRepository {
    fun retrieveCharacters(page: Int): Single<List<Character>>
    fun retrieveCharacter(id: Int): Single<Character>
    fun retrieveEpisodes(page: Int): Single<List<Episode>>
}