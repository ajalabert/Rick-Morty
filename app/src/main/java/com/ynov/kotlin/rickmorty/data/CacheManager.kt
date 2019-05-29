package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode
import io.reactivex.Single

class CacheManager : IManager {

    // TODO on préfère mettre l'interface Map<> en type de variable et utiliser emptyMap() pour initialiser une hashmap vide
    private var episodes: HashMap<Int, MutableList<Episode>> = hashMapOf()
    private var characters: HashMap<Int, MutableList<Character>> = hashMapOf()
    private var character: HashMap<Int, Character> = hashMapOf()

    override fun retrieveCharacters(page: Int): Single<List<Character>> = Single.just(this.characters[page])

    override fun retrieveCharacter(id: Int): Single<Character> = Single.just(character[id])

    override fun retrieveEpisodes(page: Int): Single<List<Episode>> = Single.just(this.episodes[page])

    fun saveInCache(characters: List<Character>, page: Int) = this.characters[page]?.addAll(characters)

    fun saveInCache(character: Character){
        this.character[character.id] = character
    }

    fun saveEpisodesInCache(episodes: List<Episode>, page: Int) = this.episodes[page]?.addAll(episodes)

    fun isCharactersInCache(page: Int) : Boolean = this.characters.containsKey(page)

    fun isCharacterInCache(id: Int) : Boolean = this.character.containsKey(id)

    fun isEpisodesInCache(page: Int) : Boolean = this.episodes.containsKey(page)

}