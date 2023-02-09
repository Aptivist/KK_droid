package com.kk.presentation.host.progressgame

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle
import com.kk.presentation.R

@Composable
fun AwaitingHostView(
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