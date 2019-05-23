package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName

enum class Status {
    Alive,
    Dead,
    @SerializedName("unknown") Unknown
}