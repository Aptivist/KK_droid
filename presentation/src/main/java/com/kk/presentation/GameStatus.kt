package com.kk.presentation

sealed interface GameStatus{
    object Idle : GameStatus
    object GameFinish: GameStatus
    object WinnerRound: GameStatus
    object NoWinnerRound: GameStatus
    object RoundFinished: GameStatus
}

fun getStatus(status: String?): GameStatus{
    return when(status){
        null -> { GameStatus.Idle }
        "NO_WINNER_ROUND" -> { GameStatus.NoWinnerRound }
        "WINNER_ROUND" -> { GameStatus.WinnerRound }
        "ROUND_FINISHED" -> { GameStatus.RoundFinished }
        "GAME_FINISHED" -> { GameStatus.GameFinish }
        else -> { throw Exception("Error") }
    }
}