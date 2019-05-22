package com.ynov.kotlin.rickmorty.data
import io.reactivex.Single
import com.ynov.kotlin.rickmorty.data.entity.models.Character as ModelCharacter
import com.ynov.kotlin.rickmorty.data.entity.remote.Character

class DataRepository(private val apiManager: ApiManager, private val cacheManager: CacheManager) {

    fun retrieveCharacters(): Single<List<ModelCharacter>> = Single.defer {
        val characters: Single<List<Character>> = if(cacheManager.isCharactersInCache()){
            cacheManager.retrieveCharacters()
        } else {
            apiManager.retrieveCharacters().doAfterSuccess {
                cacheManager.saveInCache(it)
            }
        }
        characters.map { it.map { character -> character.toModel() } }
    }

    fun retrieveCharacter(id: Long): Single<ModelCharacter> = Single.defer {
        val characters: Single<Character> = if(cacheManager.isCharacterInCache(id)){
            cacheManager.retrieveCharacter(id)
        } else {
            apiManager.retrieveCharacter(id).doAfterSuccess {
                cacheManager.saveInCache(it)
            }
        }
        characters.map { it.toModel() }
    }
}