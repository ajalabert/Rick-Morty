package com.ynov.kotlin.rickmorty.presentation

import android.app.Application
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.CacheManager
import com.ynov.kotlin.rickmorty.data.DataRepository


class RmApplication : Application() {
    companion object {
        lateinit var app: RmApplication
    }

    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()
        app = this

        initInjection()
    }

    private fun initInjection() {
        var apiManager = ApiManager()
        var cacheManager = CacheManager()
        dataRepository = DataRepository(apiManager, cacheManager)
    }
}