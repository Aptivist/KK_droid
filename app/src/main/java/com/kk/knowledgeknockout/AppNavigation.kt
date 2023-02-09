package com.kk.knowledgeknockout

sealed class AppNavigation(val route : String){
    object ScreenHome : AppNavigation("home")
    object CreateRoom : AppNavigation("create_room")
    object JoinRoom : AppNavigation("join_room")
    object WaitingRoomHost : AppNavigation("waiting_room_host")
    object WaitingRoomPlayer : AppNavigation("waiting_room_player")
    object PrestartGameHost : AppNavigation("start_game_host")
    object StartGamePlayer : AppNavigation("start_game_player")
    object StartRoundHost : AppNavigation("start_round_host")
    object StartRoundPlayer : AppNavigation("start_round_player")
    object WaitingAnswerHost : AppNavigation("waiting_answer_host")
    object WaitingAnswerPlayer : AppNavigation("waiting_answer_player")
    object SendAnswer : AppNavigation("send_answer")
    object GetAnswers : AppNavigation("get_answers")
    object EndRoundHost : AppNavigation("end_round_host")
    object EndRoundPlayer : AppNavigation("end_round_player")
    object ProgressGame : AppNavigation("progress_game_view")
}
