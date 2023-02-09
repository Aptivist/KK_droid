package com.kk.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.*

@Composable
fun PreStartAdminView(
    navigateToWaitingView : () -> Unit
){
    //viewmodel round: String
    val round = "1"
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Box(modifier = Modifier.padding(25.dp)) {
            KkBody(label = stringResource(id = R.string.question_to_ask))
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            KkOrangeTitle(label = stringResource(id = R.string.start_round))
        }
    }
}

@Composable
fun AwaitingViewHost(
    navigateToWaitingAnswerHost: () -> Unit
){
    //VIEWMODEL round: String, timeLeft: String, body: String
    val round = "1"
    val timeLeft = "43"
    val body = "dassd"

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Box(modifier = Modifier.padding(25.dp)) {
            KkBody(label = body)
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            KkOrangeTitle(label = timeLeft)
            KkOrangeTitle(label = stringResource(id = R.string.timing))
        }
    }
}

@Composable
fun AwaitingViewPlayer(
    navigateToWaitingAnswerPlayer: () -> Unit
){
    //VIEWMODEL round: String, timeLeft: String, body: String
    val round = "1"
    val timeLeft = "43"
    val body = "dassd"

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Box(modifier = Modifier.padding(25.dp)) {
            KkBody(label = body)
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            KkOrangeTitle(label = timeLeft)
            KkOrangeTitle(label = stringResource(id = R.string.timing))
        }
    }
}

@Composable
fun ShowWinnerAdminView(
    navigateToNextRound: () -> Unit
){
    //ViewModel round: String, winnerName: String
    val round = "1"
    val winnerName = "Irving"
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7F), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            KkOrangeTitle(label = stringResource(id = R.string.for_to))
            KkOrangeTitle(label = "$winnerName!")
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
            KkButton(onClick = { }, label = stringResource(id = R.string.next_button), modifier = Modifier.fillMaxWidth())
        }

    }
}

@Composable
fun RateAnswerAdminView(
    navigateToEndRoundHost: () -> Unit
){
    //VIEWMODEL round: String, playerAnswer: String, showSkip:Boolean
    val round = "1"
    val playerAnswer = "bla bla bla"
    val showSkip = false

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Box(modifier = Modifier.padding(25.dp, 0.dp)) {
            KkBody(label = stringResource(id = R.string.player_answer))
        }
        Box(modifier = Modifier.padding(25.dp)) {
            KkBody(label = playerAnswer)
        }
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 30.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom) {
            AnimatedVisibility(visible = showSkip) {
                KkButton(onClick = { }, label = stringResource(id = R.string.skip))
            }
            AnimatedVisibility(visible = !showSkip) {
                KkIncorrectButton(
                    onClick = { },
                    label = stringResource(id = R.string.incorrect_answer_button)
                )

            }
            AnimatedVisibility(visible = !showSkip) {
                KkCorrectButton(
                    onClick = { },
                    label = stringResource(id = R.string.correct_answer_button)
                )
            }
        }
    }
}