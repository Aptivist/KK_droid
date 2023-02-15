package com.kk.knowledgeknockout

import android.app.Application
import com.kk.data.di.gsonModule
import com.kk.data.di.repositoryModule
import com.kk.network.di.socketServiceModule
import com.kk.network.di.networkModuleKtor
import com.kk.presentation.di.stringModule
import com.kk.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                socketServiceModule,
                networkModuleKtor,
                viewModelModule,
                repositoryModule,
                gsonModule,
                stringModule
            )
        }
    }
}