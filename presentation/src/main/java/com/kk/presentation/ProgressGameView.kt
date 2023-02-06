package com.kk.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle

@Composable
fun PreStartAdminView(round: String){
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
fun AwaitingView(round: String, timeLeft: String, body: String){
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
fun ShowWinnerAdminView(round: String, winnerName: String){
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