package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName
import com.ynov.kotlin.rickmorty.data.entity.models.Episode

data class Episode (
    val id: Int,
    val name: String,
    @SerializedName("air_date") var airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
{
    fun toModel() : Episode = Episode(id, name, episode, airDate)
}