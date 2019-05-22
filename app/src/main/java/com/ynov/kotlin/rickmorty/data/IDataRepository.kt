package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.models.Character
import com.ynov.kotlin.rickmorty.data.entity.models.Episode
import io.reactivex.Single

interface IDataRepository {
    fun retrieveCharacters(): Single<List<Character>>
    fun retrieveCharacter(id: Long): Single<Character>
    fun retrieveEpisodes(): Single<List<Episode>>
}