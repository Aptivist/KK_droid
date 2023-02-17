package com.kk.data.di

import com.kk.data.repository.*
import com.kk.domain.repository.ResultGameRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::CreateRoomRepositoryImp){
        bind<CreateRoomRepository>()
    }
    singleOf(::HomeRepositoryImp){
        bind<HomeRepository>()
    }
    singleOf(::PlayerWaitingRoomImp){
        bind<PlayerWaitingRoomRepository>()
    }
    singleOf(::WaitingRoomAdminRepositoryImp){
        bind<WaitingRoomAdminRepository>()
    }

    singleOf(::ResultGameRepositoryImp){
        bind<ResultGameRepository>()
    }

    singleOf(::JoinRoomRepositoryImp){
        bind<JoinRoomRepository>()
    }
}