package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.CharactersResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager : IManager {

    private val service: ApiService

    interface ApiService {
        @GET("api/character/")
        fun retrieveCharacters(): Single<CharactersResult>

        @GET("api/character/{id}")
        fun retrieveCharacter(@Path("id") id: Long): Single<Character>
    }

    override fun retrieveCharacters(): Single<List<Character>> =
        service.retrieveCharacters().map{
            it.results
        }

    override fun retrieveCharacter(id: Long): Single<Character> =
        service.retrieveCharacter(id)

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}