package com.kk.local.di

import com.kk.local.data.PreferencesRepositoryImpl
import com.kk.local.domain.PreferencesRepository
import org.koin.dsl.module


val dataStoreModule = module {
    single<PreferencesRepository> {
        PreferencesRepositoryImpl(get())
    }
}