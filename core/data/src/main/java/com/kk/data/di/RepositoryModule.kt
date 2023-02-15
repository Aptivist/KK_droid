package com.kk.data.di

import com.kk.data.repository.CreateRoomRepository
import com.kk.data.repository.CreateRoomRepositoryImp
import com.kk.data.repository.HomeRepository
import com.kk.data.repository.HomeRepositoryImp
import com.kk.data.repository.WaitingRoomAdminRepository
import com.kk.data.repository.WaitingRoomAdminRepositoryImp
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
    singleOf(::WaitingRoomAdminRepositoryImp){
        bind<WaitingRoomAdminRepository>()
    }
}