package com.kk.presentation.di

import com.kk.data.repository.QRGeneratorImp
import com.kk.domain.models.QRGenerator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val qrModule = module {
    singleOf(::QRGeneratorImp){
        bind<QRGenerator>()
    }
}