package com.ynov.kotlin.rickmorty.data.entity.remote

data class EpisodesResult (
    val info: Info,
    val results: List<Episode>
)