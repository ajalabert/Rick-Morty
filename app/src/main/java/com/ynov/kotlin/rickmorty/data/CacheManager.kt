package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import io.reactivex.Single

class CacheManager : IManager {

    private var characters: MutableList<Character> = mutableListOf()
    private var character: HashMap<Long, Character> = hashMapOf()

    override fun retrieveCharacters(): Single<List<Character>> {
        return Single.just(this.characters)
    }

    override fun retrieveCharacter(id: Long): Single<Character> = Single.just(character[id])

    fun saveInCache(characters: List<Character>){
        this.characters.clear()
        this.characters.addAll(characters)
    }

    fun saveInCache(character: Character){
        this.character[character.id] = character
    }

    fun isCharactersInCache() : Boolean = this.characters.isNotEmpty()

    fun isCharacterInCache(id: Long) : Boolean = this.character.containsKey(id)

}