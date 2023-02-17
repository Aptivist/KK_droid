package com.kk.presentation.host.progressgame

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.KKBox
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle
import com.kk.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowWinnerAdminView(
    navigateToNextRound: () -> Unit,
    viewModel: ProgressGameViewModel = koinViewModel()
){
    //ViewModel round: String, winnerName: String
    val round = "1"
    val winnerName = "Irving"

    val uiState by viewModel.uiState.collectAsState()

    KKBox{
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                KkTitle(label = round/*viewModel.round*/)
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7F), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                KkOrangeTitle(label = stringResource(id = R.string.for_to))
                KkOrangeTitle(label = "$winnerName!")
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom) {
                KkButton(onClick = { /*viewModel.setEvent(ContractProgressGame.Event.NextRound)*/ },
                    label = stringResource(id = R.string.next_button),
                    modifier = Modifier.fillMaxWidth())
            }
        }
    }
}