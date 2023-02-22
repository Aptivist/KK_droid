package com.kk.presentation.di

import com.kk.presentation.home.HomeVieModel
import com.kk.presentation.host.creategame.CreateRoomViewModel
import com.kk.presentation.host.progressgame.ProgressGameViewModel
import com.kk.presentation.host.progressgame.rateanswers.RateAnswerHostViewModel
import com.kk.presentation.host.waitingroomadmin.WaitingRoomAdminViewModel
import com.kk.presentation.player.gameroom.AnswerViewModel
import com.kk.presentation.host.progressgame.winner.ShowWinnerAdminViewModel
import com.kk.presentation.player.gameroom.userquestionbutton.UserQuestionButtonViewModel
import com.kk.presentation.player.joinroom.JoinRoomViewModel
import com.kk.presentation.player.resultroom.ResultRoomViewModel
import com.kk.presentation.player.waitingroom.WaitingRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.kk.presentation.player.gameroom.userquestionbutton.UserQuestionButtonViewModel


val viewModelModule = module {
    viewModelOf(::HomeVieModel)
    viewModelOf(::CreateRoomViewModel)
    viewModelOf(::WaitingRoomViewModel)
    viewModelOf(::WaitingRoomAdminViewModel)
    viewModelOf(::ProgressGameViewModel)
    viewModelOf(::UserQuestionButtonViewModel)
    viewModelOf(::RateAnswerHostViewModel)
    viewModelOf(::ShowWinnerAdminViewModel)
    viewModelOf(::ResultRoomViewModel)
    viewModelOf(::JoinRoomViewModel)
    viewModelOf(::AnswerViewModel)
}