package com.kk.presentation.di

import com.kk.presentation.home.HomeVieModel
import com.kk.presentation.host.creategame.CreateRoomViewModel
import com.kk.presentation.host.waitingroomadmin.WaitingRoomAdminViewModel
import com.kk.presentation.player.resultroom.ResultRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::HomeVieModel)
    viewModelOf(::CreateRoomViewModel)
    viewModelOf(::WaitingRoomAdminViewModel)









    viewModelOf(::ResultRoomViewModel)
}