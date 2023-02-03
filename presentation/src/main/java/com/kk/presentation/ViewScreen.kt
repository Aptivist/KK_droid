package com.kk.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle

@Composable
fun DisplayPreStartAdminScreen(round: String){
    Column() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Box(modifier = Modifier.padding(25.dp)) {
            KkBody(label = stringResource(id = R.string.question_to_ask))
        }
        Spacer(modifier = Modifier.height(275.dp))
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            KkOrangeTitle(label = stringResource(id = R.string.start_round))
        }
    }
}

@Composable
fun DisplayAwaitingScreen(round: String, timeLeft: String, body: String){
    Column() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Box(modifier = Modifier.padding(25.dp)) {
            KkBody(label = body)
        }
        Spacer(modifier = Modifier.height(250.dp))
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            KkOrangeTitle(label = timeLeft)
            KkOrangeTitle(label = stringResource(id = R.string.timing))
        }
    }
}

@Composable
fun DisplayWinnerAdmin(modifier: Modifier = Modifier, round: String, winnerName: String){
    Column() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkTitle(label = round)
        }
        Spacer(modifier = Modifier.height(250.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            KkOrangeTitle(label = stringResource(id = R.string.for_to))
            KkOrangeTitle(label = "$winnerName!")
            Spacer(modifier = Modifier.height(250.dp))
            KkButton(onClick = { }, label = stringResource(id = R.string.next_button), modifier = Modifier.fillMaxWidth())
        }
    }
}