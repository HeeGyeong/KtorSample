package com.example.ktorsample.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val display: Int,
    val items: List<DataEntity>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
)