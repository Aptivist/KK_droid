package com.kk.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.*

@Composable
fun UserAnswerView(
    navigateToWaitingPlayers: () -> Unit
) {
    var answerValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        KkTitle(
            label = stringResource(R.string.ua_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        KkOrangeTitle(
            label = stringResource(R.string.ua_time),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(15.dp))
        KkBody(label = stringResource(R.string.ua_user_answer))
        Spacer(modifier = Modifier.size(10.dp))
        KkTextField(
            value = answerValue,
            onValueChange = { answerValue = it },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(25.dp))
        KkButton(
            onClick = { navigateToWaitingPlayers.invoke() },
            label = stringResource(R.string.ua_send_answer),
            modifier = Modifier
                .padding(bottom = 50.dp)
                .fillMaxWidth()
        )
    }

}