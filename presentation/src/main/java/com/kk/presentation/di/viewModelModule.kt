package com.kk.presentation.di

import com.kk.presentation.home.HomeVieModel
import com.kk.presentation.host.creategame.CreateRoomViewModel
import com.kk.presentation.host.waitingroomadmin.WaitingRoomAdminViewModel
import com.kk.presentation.player.resultroom.ResultRoomViewModel
import com.kk.presentation.player.waitingroom.WaitingRoomViewModel
import com.kk.presentation.player.joinroom.JoinRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.kk.presentation.player.gameroom.userquestionbutton.UserQuestionButtonViewModel


val viewModelModule = module {
    viewModelOf(::HomeVieModel)
    viewModelOf(::CreateRoomViewModel)
    viewModelOf(::WaitingRoomViewModel)
    viewModelOf(::WaitingRoomAdminViewModel)
    viewModelOf(::UserQuestionButtonViewModel)
    viewModelOf(::ResultRoomViewModel)
    viewModelOf(::JoinRoomViewModel)
}