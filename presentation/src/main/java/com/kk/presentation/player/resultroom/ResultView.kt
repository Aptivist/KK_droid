package com.kk.presentation.player.resultroom

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kk.designsystem.components.*
import com.kk.designsystem.theme.RedSalsa
import com.kk.designsystem.theme.ShamrockGreen
import com.kk.designsystem.theme.Snow
import com.kk.presentation.GameStatus
import com.kk.presentation.R
import com.kk.presentation.getStatus
import org.koin.androidx.compose.koinViewModel

@Composable
fun ResultView(
    navigateToNextRound: () -> Unit,
    viewModel: ResultRoomViewModel = koinViewModel()
){
    val uiState by viewModel.uiState.collectAsState()

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

    when(uiState.status){
        "NO_WINNER_ROUND" -> {

        }
        "WINNER_ROUND" -> {
            viewColor = ShamrockGreen
            viewAnimation = correctAnswerComposition
            viewMessage = stringResource(R.string.correct_answer)
            animationSize = 400.dp
        }
        "ROUND_FINISHED" -> {
            viewColor = RedSalsa
            viewAnimation = wrongAnswerComposition
            uiState.data.roundPlayerWon?.name.let {
                viewMessage = stringResource(R.string.round_winner, it.toString())
            }
            animationSize = 400.dp
        }
        "GAME_FINISHED" -> {
            viewTitle = stringResource(R.string.results)
            bottomText = stringResource(R.string.home)
            val winner = uiState.data.listPlayers?.first()
            if (winner?.name == "Irving"){
                viewColor = ShamrockGreen
                viewAnimation = winnerComposition
                viewMessage = stringResource(R.string.you_won)
                animationSize = 400.dp
            } else {
                viewColor = RedSalsa
                viewAnimation = null
                viewMessage = stringResource(R.string.you_lose)
                animationSize = 200.dp
            }
        }
        else -> {
            viewTitle = stringResource(R.string.round_n, "1")
            bottomText = stringResource(R.string.waiting_for_host)
        }
    }

//    KnowledgeKnockoutTheme {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize(),
//            color = viewColor.copy(alpha = 0.2F)
//        ) {
//            Column(
//                verticalArrangement = Arrangement.SpaceBetween,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    KkTitle(
//                        label = viewTitle,
//                        modifier = Modifier
//                            .padding(vertical = 40.dp)
//                    )
//
//                    Box(modifier = Modifier.height(animationSize)) {
//                        LottieAnimation(composition = viewAnimation, iterations = 1)
//                    }
//
//                    displayMessage(viewMessage, viewColor, uiState.data.listPlayers)
//                }
//
//                Row(
//                    modifier = Modifier
//                        .padding(40.dp)
//                        .weight(1f, false)
//                ) {
//                    displayFooter(bottomText, false)
//                }
//            }
//        }
//    }
}

//@Composable
//fun displayFooter(bottomText: String, gameOver: Boolean) {
//    if(!gameOver){
//        KkBody(
//            label = bottomText
//        )
//    }
//    else{
//        KkButton(
//            onClick = {  },
//            label = bottomText
//        )
//    }
//}
//
//@Composable
//fun displayMessage(message: String, color: Color, players: List<PlayerUserDomain>?) {
//    if(color == ShamrockGreen){
//        KkCorrectTitle(
//            label = message
//        )
//    }
//    else{
//        KkIncorrectTitle(
//            label = message
//        )
//
//        if(message == stringResource(R.string.you_lose)){
//            for(player in players){
//                KkBody(
//                    label = stringResource(
//                        R.string.player_and_score, player.first, player.third
//                    )
//                )
//            }
//        }
//    }
//}