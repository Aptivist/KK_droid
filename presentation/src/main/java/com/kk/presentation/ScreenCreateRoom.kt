package com.kk.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkNumberField
import com.kk.designsystem.components.KkTitle

@Composable
fun ScreenCreateRoom() {
    var participantValue by remember { mutableStateOf("") }
    var pointValue by remember { mutableStateOf("") }
    var timerValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        KkTitle(
            label = stringResource(R.string.create_room),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 40.dp)
        )
        KkBody(label = stringResource(R.string.participants))
        Spacer(modifier = Modifier.size(10.dp))
        KkNumberField(
            value = participantValue,
            onValueChange = {
                participantValue = if (it.isDigitsOnly()) it else participantValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))
        KkBody(label = stringResource(R.string.points))
        Spacer(modifier = Modifier.size(10.dp))
        KkNumberField(
            value = pointValue,
            onValueChange = {
                pointValue = if (it.isDigitsOnly()) it else pointValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))
        KkBody(label = stringResource(R.string.time))
        Spacer(modifier = Modifier.size(10.dp))
        KkNumberField(
            value = timerValue,
            onValueChange = {
                timerValue = if (it.isDigitsOnly()) it else timerValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(0.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        KkButton(
            onClick = { /*TODO*/ },
            label = stringResource(R.string.create),
            modifier = Modifier
                .padding(vertical = 50.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}