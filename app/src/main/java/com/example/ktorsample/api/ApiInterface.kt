package com.example.ktorsample.api

import com.example.ktorsample.model.ApiRequest
import com.example.ktorsample.model.DataEntity

interface ApiInterface {
    suspend fun requestMoveSearch(query: String, start: Int = 1, display: Int = 15): String
    suspend fun requestMoveSearchPost(apiRequest: ApiRequest): String
    suspend fun requestMoveSearchData(
        query: String,
        start: Int = 1,
        display: Int = 15,
    ): List<DataEntity>
}