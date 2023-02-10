package com.kk.knowledgeknockout

import android.app.Application
import com.kk.data.di.socketServiceModule
import com.kk.network.di.networkModuleKtor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@App)

            modules(socketServiceModule, networkModuleKtor)
        }
    }
}