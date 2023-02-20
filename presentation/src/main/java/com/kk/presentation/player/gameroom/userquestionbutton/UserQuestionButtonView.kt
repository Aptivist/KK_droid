package com.kk.presentation.player.gameroom.userquestionbutton

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            KkTitle(
                label = "" + round + stringResource(id = R.string.uqb_st) + " " + stringResource(
                    id = R.string.uqb_title
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 40.dp)
            )
            Spacer(modifier = Modifier.size(50.dp))
            Box {
                Column(modifier = Modifier.zIndex(uiState.zIndex)) {
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
                    Spacer(modifier = Modifier.size(30.dp))
                }
                Text(text = "Esperando al host...", modifier = Modifier.zIndex(1f))
            }

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