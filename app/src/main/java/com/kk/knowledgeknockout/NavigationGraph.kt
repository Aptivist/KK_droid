package com.kk.knowledgeknockout

import com.kk.presentation.player.joinroom.JoinRoomView
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kk.presentation.*
import com.kk.presentation.home.HomeView
import com.kk.presentation.host.creategame.CreateRoomView
import com.kk.presentation.host.progressgame.*
import com.kk.presentation.host.progressgame.rateanswers.RateAnswerAdminView
import com.kk.presentation.host.waitingroomadmin.WaitingRoomAdminView
import com.kk.presentation.player.resultroom.ResultView
import com.kk.presentation.player.gameroom.userquestionbutton.UserQuestionButtonView
import com.kk.presentation.player.gameroom.AnswerView
import com.kk.presentation.player.waitingroom.WaitingRoomPlayerView


@Composable
fun NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigation.ScreenHome.route){

        composable(route = AppNavigation.ScreenHome.route){

            HomeView(
                navigateToCreateRoom = {
                    navController.navigate(AppNavigation.CreateRoom.route){
                        popUpTo(AppNavigation.ScreenHome.route)
                    }

                },
                navigateToJoinRoom = {
                    navController.navigate(AppNavigation.JoinRoom.route)

                }
            )
        }
        composable(route = AppNavigation.CreateRoom.route){
            CreateRoomView(
                navigateToHome = {
                    navController.navigate(AppNavigation.ScreenHome.route)
                },
                navigateToWaitingRoom = {
                    navController.navigate(AppNavigation.WaitingRoomHost.route+"/$it")
                }
            )
        }
        composable(route = AppNavigation.JoinRoom.route){
            JoinRoomView(
                navigateToHome = {
                    navController.navigate(AppNavigation.ScreenHome.route)
                },
                navigateToWaitingRoom = {
                    navController.navigate(AppNavigation.WaitingRoomPlayer.route)
                }
            )
        }
        composable(
            route = AppNavigation.WaitingRoomHost.route+"/{code}",
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ){
            val code  = it.arguments?.getString("code","")
            WaitingRoomAdminView(
                codeRoom = code?:"NO CODE",
                onBackCreateRoom = {
                    navController.navigate(AppNavigation.CreateRoom.route)
                },
                navigateToStartGameHost = {
                    navController.navigate(AppNavigation.PrestartGameHost.route)
                }
            )
        }
        composable(route = AppNavigation.WaitingRoomPlayer.route){
            WaitingRoomPlayerView(
                navigateToHome = {
                    navController.navigate(AppNavigation.ScreenHome.route)
                },
                navigateToStartGamePlayer = {
                    navController.navigate(AppNavigation.StartGamePlayer.route)
                }
            )
        }
        composable(route = AppNavigation.PrestartGameHost.route){
            PreStartAdminView(
                navigateToRateAnswerAdminView = {
                    navController.navigate(AppNavigation.WaitingAnswerHost.route)
                }
            )
        }
        composable(route = AppNavigation.WaitingAnswerHost.route){
            RateAnswerAdminView(
                navigateToEndRoundHost = {
                    navController.navigate(AppNavigation.EndRoundHost.route)
                }
            )
        }
        composable(route = AppNavigation.EndRoundHost.route){
            ShowWinnerAdminView(
                navigateToNextRound = {
                    navController.navigate(AppNavigation.PrestartGameHost.route)
                }
            )
        }
        composable(route = AppNavigation.StartGamePlayer.route){
            UserQuestionButtonView(
                navigateToHome = {
                    AppNavigation.ScreenHome.route
                },
                navigateToSendAnswer = {
                    navController.navigate(AppNavigation.SendAnswer.route+"/$it")
                },
                navigateToWaitingPlayers = {
                    navController.navigate(AppNavigation.WaitingAnswerPlayer.route)
                }
            )
        }
        composable(route = AppNavigation.SendAnswer.route+"/{timeStamp}",arguments = listOf(navArgument("timeStamp") { type = NavType.LongType })){
            // timeStamp is the system time when the user push the main button
            val timeStamp  = it.arguments?.getLong("timeStamp",0)?:0
            AnswerView(
                timeStamp = timeStamp,
                navigateToHome = {
                    AppNavigation.ScreenHome.route
                },
                navigateToWaitingPlayers = {
                    navController.navigate(AppNavigation.EndRoundPlayer.route)
                }
            )
        }
        composable(route = AppNavigation.WaitingAnswerPlayer.route){
            AwaitingViewPlayer(
                navigateToWaitingAnswerPlayer = {
                    navController.navigate(AppNavigation.EndRoundPlayer.route)
                }
            )
        }
        composable(route = AppNavigation.EndRoundPlayer.route){
            ResultView(
                navigateToNextRound = {
                    navController.navigate(AppNavigation.StartGamePlayer.route)
                }
            )
        }
    }
}
