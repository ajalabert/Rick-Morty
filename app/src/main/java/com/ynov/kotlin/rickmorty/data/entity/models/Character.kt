package com.ynov.kotlin.rickmorty.data.entity.models

data class Character(
    var id: Int,
    var name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    var location: String
)