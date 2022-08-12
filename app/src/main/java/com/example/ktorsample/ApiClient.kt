package com.example.ktorsample

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ApiClient {
    companion object {
        const val BASE_URL = "https://openapi.naver.com/"
    }

    private val client = HttpClient(CIO) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("ktorLogger", "message : $message")
                }
            }
//            logger = Logger.DEFAULT

            level = LogLevel.ALL
        }

        // JsonFeature > ContentNegotiation
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            })

            // implementation "io.ktor:ktor-serialization-gson:$ktor_version"
//            gson {  }
            // implementation "io.ktor:ktor-serialization-jackson:$ktor_version"
//            jackson {  }
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 6000
            requestTimeoutMillis = 6000
            socketTimeoutMillis = 6000
        }

        defaultRequest {
            contentType(ContentType.Application.Json)

            headers {
                append("X-Naver-Client-Id", "33chRuAiqlSn5hn8tIme")
                append("X-Naver-Client-Secret", "fyfwt9PCUN")
            }
        }
    }

    suspend fun requestMoveSearch(query: String, start: Int = 1, display: Int = 15): String =
        withContext(Dispatchers.IO) {
            val response: HttpResponse =
                client.get {
                    url(BASE_URL + "v1/search/movie.json")
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

    suspend fun requestMoveSearchData(
        query: String,
        start: Int = 1,
        display: Int = 15,
    ): List<DataEntity> =
        client.get(BASE_URL + "v1/search/movie.json") {
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
    suspend fun requestMoveSearchPost(apiRequest: ApiRequest) =
        client.post(BASE_URL + "v1/search/movie.json") {
            setBody(apiRequest)
        }.bodyAsText()
}