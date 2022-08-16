package com.example.ktorsample.api

import com.example.ktorsample.model.ApiRequest
import com.example.ktorsample.model.ApiResponse
import com.example.ktorsample.model.DataEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val client: HttpClient) : ApiInterface {

    override suspend fun requestMoveSearch(
        query: String,
        start: Int,
        display: Int,
    ): String =
        withContext(Dispatchers.IO) {
            val response: HttpResponse =
                client.get {
                    url(BASE_URL)
                    method = HttpMethod.Get

                    parameter("query", query)
                    parameter("start", start)
                    parameter("display", display)
                }

            val responseStatus = response.status

            if (responseStatus == HttpStatusCode.OK) {
                response.bodyAsText()
            } else {
                "Error :: $responseStatus"
            }
        }

    override suspend fun requestMoveSearchData(
        query: String,
        start: Int,
        display: Int,
    ): List<DataEntity> =
        client.get(BASE_URL) {
            parameter("query", query)
            parameter("start", start)
            parameter("display", display)
        }.body<ApiResponse>().items

    /**
     * post 방식으로 통신할 경우, Request object를 만들어서 setBody를 통해 설정해준다.
     *
     * 해당 api에서는 post를 사용하지 않아 에러가 발생한다.
     *
     * Log로 데이터 확인.
     */
    override suspend fun requestMoveSearchPost(apiRequest: ApiRequest): String =
        client.post(BASE_URL) {
            setBody(apiRequest)
        }.bodyAsText()

    companion object {
        const val BASE_URL = "https://openapi.naver.com/v1/search/movie.json"
    }
}