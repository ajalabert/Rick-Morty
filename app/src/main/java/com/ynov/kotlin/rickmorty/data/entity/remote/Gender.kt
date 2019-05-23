package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName

enum class Gender {
    Female,
    Male,
    Genderless,
    @SerializedName("unknown") Unknown
}