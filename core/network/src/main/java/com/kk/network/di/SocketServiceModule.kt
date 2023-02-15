package com.kk.network.di

import com.kk.network.service.ISocketService
import com.kk.network.service.SocketServiceImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val socketServiceModule = module {
    singleOf(::SocketServiceImp){
        bind<ISocketService>()
    }


}