package com.example.ktorsample

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse (
    val display: Int,
    val items: List<DataEntity>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)