package com.example.ktorsample.module

import android.util.Log
import com.example.ktorsample.api.ApiInterface
import com.example.ktorsample.api.ApiService
import com.example.ktorsample.view.MainViewModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule: Module = module {
    singleOf(::MainViewModel)
//    viewModel { MainViewModel(get()) }

    singleOf(::ApiService) bind ApiInterface::class

    single {
        HttpClient(CIO) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("ktorLogger", "message : $message")
                    }
                }
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                })
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
    }
}