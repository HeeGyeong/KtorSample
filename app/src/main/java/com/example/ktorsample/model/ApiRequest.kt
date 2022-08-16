package com.example.ktorsample.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val query: String,
    val start: Int = 1,
    val display: Int = 15,
)