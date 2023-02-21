package com.kk.presentation.player.gameroom.userquestionbutton

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.kk.designsystem.R.drawable.main_button
import com.kk.designsystem.components.KKBox
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserQuestionButtonView(
    round: Int = 1,
    navigateToSendAnswer: (timeStamp: Int) -> Unit,
    navigateToWaitingPlayers: () -> Unit,
    viewModel: UserQuestionButtonViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val image = painterResource(id = main_button)
    KKBox() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .zIndex(uiState.zIndex)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            KkTitle(
                label = "" + round + stringResource(id = R.string.uqb_st) + " " + stringResource(
                    id = R.string.uqb_title
                )
            )
            Spacer(modifier = Modifier.size(50.dp))

            KkOrangeTitle(
                label = uiState.timer.toString() + " " + stringResource(id = R.string.uqb_time),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(40.dp))
            Image(
                painter = image,
                contentDescription = stringResource(R.string.uqb_main_button),
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(enabled = uiState.roundStarted) {
                        viewModel.handleEvent(
                            UserQuestionButtonContract.Event.OnMainButtonClicked
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
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
                .background(MaterialTheme.colorScheme.background)
                .alpha(0.4f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.uqb_waiting_host),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                UserQuestionButtonContract.Effect.NavigateToSendPlayerAnswer -> navigateToSendAnswer(
                    uiState.timeStamp
                )
                UserQuestionButtonContract.Effect.NavigateToWaitingPlayers -> navigateToWaitingPlayers()
            }
        }
    }

}