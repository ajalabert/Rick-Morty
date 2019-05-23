package com.ynov.kotlin.rickmorty.data.entity.models

data class Episode(
    var id: Int,
    var name: String,
    val episode: String,
    var airDate: String
)