package com.kk.network.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*

/*val networkModuleKtor = module {
    singleOf(::provideHttpClient)
}*/

fun provideHttpClient() = HttpClient(CIO){
    install(Logging)
    install(WebSockets)
    //install(JsonFeature)
}