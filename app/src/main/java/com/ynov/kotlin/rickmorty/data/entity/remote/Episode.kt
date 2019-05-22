package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName
import com.ynov.kotlin.rickmorty.data.entity.models.Episode

data class Episode (
    val id: Long,
    val name: String,
    @SerializedName("air_date") var airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
{
    fun toModel() : Episode = Episode(this.id, this.name, this.episode, this.airDate)
}