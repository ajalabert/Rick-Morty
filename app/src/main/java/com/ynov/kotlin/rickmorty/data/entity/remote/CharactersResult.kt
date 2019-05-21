package com.ynov.kotlin.rickmorty.data.entity.remote

data class CharactersResult (
    val info: Info,
    val results: List<Character>
)