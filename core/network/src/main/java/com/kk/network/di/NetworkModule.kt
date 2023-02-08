package com.kk.network.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModuleKtor = module {
    singleOf(::provideHttpClient)
}

fun provideHttpClient() = HttpClient(CIO){
    install(Logging)
    install(WebSockets)
    install(JsonFeature){
        serializer = KotlinxSerializer()
    }
}