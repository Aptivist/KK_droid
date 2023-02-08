package com.kk.knowledgeknockout

sealed class AppScreens(val route : String){
    object ScreenHome : AppScreens("home_screen")
    object ScreenJoinRoom : AppScreens("join_room_screen")
    object ProgressGameView : AppScreens("progress_game_screen")
    object ScreenCreateRoom : AppScreens("create_room_screen")
}
