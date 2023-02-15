package com.kk.presentation.di

import android.content.Context
import androidx.annotation.StringRes
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val stringModule = module {
    singleOf(::StringProviderImp){
        bind<StringProvider>()
    }

}


class StringProviderImp (private val context: Context): StringProvider{
    override fun getString(idString: Int, vararg parameters: Any): String {
        return context.getString(idString, *parameters)
    }

}

interface StringProvider {
    fun getString(@StringRes idString: Int, vararg parameters: Any): String
}
