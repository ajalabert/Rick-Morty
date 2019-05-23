package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.CharactersResult
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode
import com.ynov.kotlin.rickmorty.data.entity.remote.EpisodesResult
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager : IManager {

    private val service: ApiService

    interface ApiService {
        @GET("api/character/")
        fun retrieveCharacters(@Query("page") page: Int): Single<CharactersResult>

        @GET("api/character/{id}")
        fun retrieveCharacter(@Path("id") id: Long): Single<Character>

        @GET("api/episode")
        fun retrieveEpisodes(): Single<EpisodesResult>
    }

    override fun retrieveCharacters(page: Int): Single<List<Character>> = service.retrieveCharacters(page).map{ it.results }

    override fun retrieveCharacter(id: Long): Single<Character> = service.retrieveCharacter(id)

    override fun retrieveEpisodes(): Single<List<Episode>> = service.retrieveEpisodes().map{ it.results }

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build())
            .build()
            .create(ApiService::class.java)
    }
}