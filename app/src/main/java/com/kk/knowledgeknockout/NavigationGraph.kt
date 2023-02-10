package com.kk.knowledgeknockout

import ScreenJoinGroup
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kk.presentation.*
import com.kk.presentation.host.progressgame.PreStartAdminView
import com.kk.presentation.host.creategame.CreateRoomView


@Composable
fun NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigation.ScreenHome.route){
        composable(route = AppNavigation.ScreenHome.route){
            ScreenHome(
                navigateToCreateRoom = {
                    navController.navigate(AppNavigation.CreateRoom.route)
                },
                navigateToJoinRoom = {
                    navController.navigate(AppNavigation.JoinRoom.route)
                }
            )
        }
        composable(route = AppNavigation.CreateRoom.route){
            CreateRoomView(
                onBackHome = {
                    navController.navigate(AppNavigation.ScreenHome.route)
                },
                navigateToWaitingRoom = {
                    navController.navigate(AppNavigation.WaitingRoomHost.route)
                }
            )
        }
        composable(route = AppNavigation.JoinRoom.route){
            ScreenJoinGroup(
                onBackHome = {
                    navController.navigate(AppNavigation.ScreenHome.route)
                },
                navigateToWaitingRoom = {
                    navController.navigate(AppNavigation.WaitingRoomHost.route)
                }
            )
        }
        composable(route = AppNavigation.WaitingRoomHost.route){
            WaitingRoomAdminView(
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
                onBackJoinRoom = {
                    navController.navigate(AppNavigation.JoinRoom.route)
                },
                navigateToStartGamePlayer = {
                    navController.navigate(AppNavigation.StartGamePlayer.route)
                }
            )
        }
        composable(route = AppNavigation.PrestartGameHost.route){
            PreStartAdminView(
                navigateToWaitingView = {
                    navController.navigate(AppNavigation.StartRoundHost.route)
                }
            )
        }
        composable(route = AppNavigation.StartRoundHost.route){
            AwaitingViewHost(
                navigateToWaitingAnswerHost = {
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
                navigateToSendAnswer = {
                    navController.navigate(AppNavigation.SendAnswer.route)
                }
            )
        }
        composable(route = AppNavigation.SendAnswer.route){
            ScreenUserAnswer(
                navigateToWaitingPlayers = {
                    navController.navigate(AppNavigation.WaitingAnswerPlayer.route)
                }
            )
        }
        composable(route = AppNavigation.WaitingAnswerPlayer.route){
            AwaitingViewHost(
                navigateToWaitingAnswerHost = {
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
