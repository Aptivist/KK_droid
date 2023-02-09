package com.kk.knowledgeknockout

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kk.presentation.UserAnswerView


@Composable
fun NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.ScreenHome.route){
        composable(route = AppScreens.ScreenHome.route){
            UserAnswerView(navController)
        }
    }
}
