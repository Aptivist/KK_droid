package com.kk.presentation.player

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kk.designsystem.components.*
import com.kk.designsystem.theme.KnowledgeKnockoutTheme
import com.kk.designsystem.theme.RedSalsa
import com.kk.designsystem.theme.ShamrockGreen
import com.kk.designsystem.theme.Snow
import com.kk.presentation.R

@Composable
fun ResultView(
    navigateToNextRound: () -> Unit
){
    //Username playing
    val userId = 2
    //Score of players (username, id, score) (from back-end)
    val score = mutableListOf(Triple("gciadiego", 1, 3), Triple("droldan", 2, 1))
    //Number of round
    val roundNumber = 3
    //True if the game is over, false if the game continues
    val gameOver = true
    //ID of player who won the round
    val roundWinner = 1

    val correctAnswerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(com.kk.designsystem.R.raw.correct_animation)
    )

    val wrongAnswerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(com.kk.designsystem.R.raw.incorrect_animation_light)
    )

    val winnerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(com.kk.designsystem.R.raw.trophy_animation)
    )

    var viewTitle = ""
    var viewColor = Snow
    var viewAnimation = correctAnswerComposition
    var animationSize = 400.dp
    var viewMessage = ""
    var bottomText = ""

    if(!gameOver){
        viewTitle = stringResource(R.string.round_n)+roundNumber
        bottomText = stringResource(R.string.waiting_for_host)
        if(roundWinner == userId){
            viewColor = ShamrockGreen
            viewAnimation = correctAnswerComposition
            viewMessage = stringResource(R.string.correct_answer)
            animationSize = 400.dp
        }else{
            viewColor = RedSalsa
            viewAnimation = wrongAnswerComposition
            viewMessage = stringResource(R.string.round_winner, score.filter { it.second == roundWinner }.map { it.first })
            animationSize = 400.dp
        }
    }else{
        viewTitle = stringResource(R.string.results)
        bottomText = stringResource(R.string.home)
        if(score.sortedBy { it.third }.last().second == userId){
            viewColor = ShamrockGreen
            viewAnimation = winnerComposition
            viewMessage = stringResource(R.string.you_won)
            animationSize = 400.dp
        }else{
            viewColor = RedSalsa
            viewAnimation = null
            viewMessage = stringResource(R.string.you_lose)
            animationSize = 200.dp
        }
    }

    KnowledgeKnockoutTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = viewColor.copy(alpha = 0.2F)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    KkTitle(
                        label = viewTitle,
                        modifier = Modifier
                            .padding(vertical = 40.dp)
                    )

                    Box(modifier = Modifier.height(animationSize)) {
                        LottieAnimation(composition = viewAnimation, iterations = 1)
                    }

                    displayMessage(viewMessage, viewColor, score)
                }

                Row(
                    modifier = Modifier
                        .padding(40.dp)
                        .weight(1f, false)
                ) {
                    displayFooter(bottomText, gameOver)
                }
            }
        }
    }
}

@Composable
fun displayFooter(bottomText: String, gameOver: Boolean) {
    if(!gameOver){
        KkBody(
            label = bottomText
        )
    }
    else{
        KkButton(
            onClick = { /*TODO*/ },
            label = bottomText
        )
    }
}

@Composable
fun displayMessage(message: String, color: Color, players: MutableList<Triple<String, Int, Int>>) {
    if(color == ShamrockGreen){
        KkCorrectTitle(
            label = message
        )
    }
    else{
        KkIncorrectTitle(
            label = message
        )

        if(message == stringResource(R.string.you_lose)){
            for(player in players){
                KkBody(
                    label = stringResource(
                        R.string.player_and_score, player.first, player.third
                    )
                )
            }
        }
    }
}