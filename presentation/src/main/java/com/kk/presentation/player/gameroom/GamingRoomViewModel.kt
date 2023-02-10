package com.kk.presentation.player.gameroom

import com.kk.presentation.baseMVI.BaseViewModel

class GamingRoomViewModel :
    BaseViewModel<GamingRoomContract.Event, GamingRoomContract.State, GamingRoomContract.Effect>() {

    override fun createInitialState(): GamingRoomContract.State {
        return GamingRoomContract.State(
            GamingRoomContract.GamingRoomState.Idle
        )
    }

    override fun handleEvent(event: GamingRoomContract.Event) {
        when (event) {
            is GamingRoomContract.Event.OnQuestionButtonClicked -> {

            }

            is GamingRoomContract.Event.OnQuestionSkipButtonClicked -> {
                skipQuestion()
            }
            is GamingRoomContract.Event.SendAnswerButtonClicked -> {
                sendAnswer()
            }
        }
    }

    private fun skipQuestion() {
        TODO("Not yet implemented")
    }

    private fun sendAnswer() {
        TODO("Not yet implemented")
    }
}