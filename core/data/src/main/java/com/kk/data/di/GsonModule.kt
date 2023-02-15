package com.kk.data.di

import com.google.gson.Gson
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val gsonModule = module {
    singleOf(::Gson)
}