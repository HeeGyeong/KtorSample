package com.example.ktorsample

import kotlinx.serialization.Serializable

@Serializable
data class DataEntity(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String,
)