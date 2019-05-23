package com.ynov.kotlin.rickmorty.data

import io.reactivex.Single
import com.ynov.kotlin.rickmorty.data.entity.models.Character as CharacterModel
import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.models.Episode as EpisodeModel
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode

class DataRepository(private val apiManager: ApiManager, private val cacheManager: CacheManager) : IDataRepository {

    override fun retrieveEpisodes(): Single<List<EpisodeModel>> = Single.defer{
        val episodes: Single<List<Episode>> = if(cacheManager.isEpisodesInCache()){
            cacheManager.retrieveEpisodes()
        } else {
            apiManager.retrieveEpisodes().doAfterSuccess {
                cacheManager.saveEpisodesInCache(it)
            }
        }
        episodes.map { it.map { episode -> episode.toModel() } }
    }

    override fun retrieveCharacters(page: Int): Single<List<CharacterModel>> = Single.defer {
        val characters: Single<List<Character>> = if(cacheManager.isCharactersInCache(page)){
            cacheManager.retrieveCharacters(page)
        } else {
            apiManager.retrieveCharacters(page).doAfterSuccess {
                cacheManager.saveInCache(it, page)
            }
        }
        characters.map { it.map { character -> character.toModel() } }
    }

    override fun retrieveCharacter(id: Long): Single<CharacterModel> = Single.defer {
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