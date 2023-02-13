package com.kk.data.di

import com.kk.data.ISocketService
import com.kk.data.SocketServiceImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val socketServiceModule = module {
    singleOf(::SocketServiceImp){
        bind<ISocketService>()
    }


}