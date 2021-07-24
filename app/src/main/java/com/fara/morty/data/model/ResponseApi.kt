package com.fara.morty.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseApi(
    val results: List<RickMorty>,
)

@Parcelize
data class RickMorty(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
) : Parcelable
