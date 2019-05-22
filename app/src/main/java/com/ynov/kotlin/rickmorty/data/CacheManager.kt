package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode
import io.reactivex.Single

class CacheManager : IManager {

    private var episodes: MutableList<Episode> = mutableListOf()
    private var characters: MutableList<Character> = mutableListOf()
    private var character: HashMap<Long, Character> = hashMapOf()

    override fun retrieveCharacters(): Single<List<Character>> = Single.just(this.characters)

    override fun retrieveCharacter(id: Long): Single<Character> = Single.just(character[id])

    override fun retrieveEpisodes(): Single<List<Episode>> = Single.just(this.episodes)

    fun saveInCache(characters: List<Character>){
        this.characters.clear()
        this.characters.addAll(characters)
    }

    fun saveInCache(character: Character){
        this.character[character.id] = character
    }

    fun saveEpisodesInCache(episodes: List<Episode>){
        this.episodes.clear()
        this.episodes.addAll(episodes)
    }

    fun isCharactersInCache() : Boolean = this.characters.isNotEmpty()

    fun isCharacterInCache(id: Long) : Boolean = this.character.containsKey(id)

    fun isEpisodesInCache() : Boolean = this.episodes.isNotEmpty()

}