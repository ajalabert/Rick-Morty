package com.ynov.kotlin.rickmorty.data.entity.models

data class Episode(
    var id: Long,
    var name: String,
    val episode: String,
    var airDate: String
)