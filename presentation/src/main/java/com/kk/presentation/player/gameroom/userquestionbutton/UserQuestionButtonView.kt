package com.kk.presentation.player.gameroom.userquestionbutton

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.R.drawable.main_button
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserQuestionButtonView(
    navigateToSendAnswer: () -> Unit,
    navigateToWaitingPlayers: () -> Unit,
    viewModel: UserQuestionButtonViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val image = painterResource(id = main_button)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        KkTitle(
            label = stringResource(R.string.uqb_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 40.dp)
        )
        Spacer(modifier = Modifier.size(50.dp))
        KkOrangeTitle(
            label = uiState.timer.toString(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Image(
            painter = image,
            contentDescription = stringResource(R.string.uqb_main_button),
            modifier = Modifier
                .height(300.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    viewModel.handleEvent(
                        UserQuestionButtonContract.Event.OnMainButtonClicked()
                    )
                }
        )
        Spacer(modifier = Modifier.size(50.dp))
        KkBody(
            label = stringResource(R.string.uqb_skip),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable() {
                    viewModel.handleEvent(UserQuestionButtonContract.Event.OnSkipButtonClicked)
                }
        )
        Spacer(modifier = Modifier.size(30.dp))

        LaunchedEffect(key1 = Unit) {
            viewModel.effect.collectLatest { effect ->
                when (effect) {
                    UserQuestionButtonContract.Effect.NavigateToSendPlayerAnswer -> navigateToSendAnswer()
                    UserQuestionButtonContract.Effect.NavigateToWaitingPlayers -> navigateToWaitingPlayers()
                }
            }
        }

    }
}