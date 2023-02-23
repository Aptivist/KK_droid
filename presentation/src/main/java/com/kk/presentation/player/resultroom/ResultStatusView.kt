package com.kk.presentation.player.resultroom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.kk.designsystem.R
import com.kk.designsystem.components.*
import com.kk.designsystem.theme.DimGray
import com.kk.designsystem.theme.PaleGreen
import com.kk.designsystem.theme.PaleRed
import com.kk.domain.models.PlayerUserDomain


@Composable
fun WaitingResultView(title: String) {
    val loadingComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.loading)
    )
    BaseViewResult(
        title = title,
        backgroundColor = DimGray,
        viewAnimation = loadingComposition,
        iterations = LottieConstants.IterateForever,
        message = stringResource(com.kk.presentation.R.string.waiting_for_host),
    )
}

@Composable
fun NoPointsRoundView(title: String) {
    val incorrectAnswerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.incorrect_animation_light)
    )
    BaseViewResult(
        title = title,
        viewAnimation = incorrectAnswerComposition,
        backgroundColor = PaleRed,
        message = stringResource(com.kk.presentation.R.string.no_points),
        isWaiting = false,
        isWinner = false,
    )
}

@Composable
fun LoserGameView(title: String, players: List<PlayerUserDomain>, onClickHome: (() -> Unit)) {
    val incorrectAnswerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.incorrect_animation_light)
    )
    BaseViewResult(
        title = title,
        viewAnimation = incorrectAnswerComposition,
        backgroundColor = PaleRed,
        message = stringResource(id = com.kk.presentation.R.string.you_lose),
        players = players,
        isWaiting = false,
        isWinner = false,
        isGameFinished = true,
        onClickHome = onClickHome
    )
}


@Composable
fun LoserRoundView(title: String, winnerName: String) {
    val incorrectAnswerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.incorrect_animation_light)
    )
    BaseViewResult(
        title = title,
        viewAnimation = incorrectAnswerComposition,
        backgroundColor = PaleRed,
        message = stringResource(id = com.kk.presentation.R.string.round_winner, winnerName),
        isWaiting = false,
        isWinner = false,
    )
}

@Composable
fun WinnerRoundView(title: String) {
    val correctAnswerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.correct_animation)
    )
    BaseViewResult(
        title = title,
        viewAnimation = correctAnswerComposition,
        backgroundColor = PaleGreen,
        message = stringResource(com.kk.presentation.R.string.correct_answer),
        isWaiting = false,
        isWinner = true,
    )
}

@Composable
fun WinnerGameView(title: String, players: List<PlayerUserDomain>, onClickHome: (() -> Unit)) {
    val winnerComposition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.trophy_animation)
    )
    BaseViewResult(
        title = title,
        viewAnimation = winnerComposition,
        backgroundColor = PaleGreen,
        message = stringResource(com.kk.presentation.R.string.you_won),
        isWaiting = false,
        isWinner = true,
        isGameFinished = true,
        players = players,
        onClickHome = onClickHome
    )
}


@Composable
fun BaseViewResult(
    title: String,
    viewAnimation: LottieComposition? = null,
    backgroundColor: Color,
    message: String,
    players: List<PlayerUserDomain> = emptyList(),
    isWaiting: Boolean = true,
    isWinner: Boolean = false,
    isGameFinished: Boolean = false,
    iterations: Int = 1,
    onClickHome: (() -> Unit)? = null
) {
    val animationSize = 300.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        KkTitle(
            label = title,
            modifier = Modifier
                .padding(vertical = 40.dp)
        )

        if (viewAnimation != null) {
            Box(
                modifier = Modifier
                    .height(animationSize)
                    .width(animationSize)
            ) {
                LottieAnimation(composition = viewAnimation, iterations = iterations)
            }
        }

        PlayerLisTResult(players = players)


        MessageResultView(message = message, isWaiting = isWaiting, isWinner = isWinner)

        AnimatedVisibility(visible = isGameFinished) {
            Spacer(modifier = Modifier.height(10.dp))
            KkButton(
                label = stringResource(com.kk.presentation.R.string.home), onClick = {onClickHome?.invoke()}
            )
        }

    }
}



@Composable
fun PlayerLisTResult(players: List<PlayerUserDomain>) {
    if (players.isNotEmpty()) {
        LazyColumn {
            items(players) { player ->
                KkBodyGray(
                    label = stringResource(
                        com.kk.presentation.R.string.player_and_score,
                        player.name, (player.points?:0).toString()
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun MessageResultView(message: String, isWaiting: Boolean, isWinner: Boolean) {
    if (isWaiting) {
        KkTitleLarge(label = message)
    } else {
        if (isWinner) {
            KkCorrectTitle(
                label = message
            )
        } else {
            KkIncorrectTitle(label = message)
        }
    }
}