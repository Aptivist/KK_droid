package com.kk.presentation.host.waitingroomadmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kk.designsystem.components.KKBox
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkChip
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle
import com.kk.designsystem.theme.BurntSienna
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun WaitingRoomAdminView(
    codeRoom: String = "No code",
    onBackCreateRoom : () -> Unit,
    navigateToStartGameHost : () -> Unit,
    waitingRoomAdminViewModel: WaitingRoomAdminViewModel = koinViewModel()
) {
    val uiState by waitingRoomAdminViewModel.uiState.collectAsState()
    KKBox {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (titleLabel, subtitleLabel, codeLabel, qrLabel, qrBox, playersLabel, chipGroup, startButton)
                    = createRefs()

            KkTitle(modifier = Modifier.constrainAs(titleLabel) {
                top.linkTo(parent.top, 25.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, label = stringResource(R.string.stringTitle))

            KkBody(modifier = Modifier.constrainAs(subtitleLabel) {
                top.linkTo(titleLabel.bottom, 20.dp)
                start.linkTo(parent.start, 25.dp)
            }, label = stringResource(R.string.stringSubtitle))

            KkOrangeTitle(modifier = Modifier.constrainAs(codeLabel) {
                top.linkTo(subtitleLabel.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, label = codeRoom)

            KkBody(modifier = Modifier.constrainAs(qrLabel) {
                top.linkTo(codeLabel.bottom, 20.dp)
                start.linkTo(parent.start, 25.dp)
            }, label = stringResource(R.string.stringQR))
            //QR
            Card(
                modifier = Modifier
                    .border(2.dp, BurntSienna, RoundedCornerShape(5.dp))
                    .constrainAs(qrBox) {
                        top.linkTo(qrLabel.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                shape = RoundedCornerShape(5.dp)
            ) {
                uiState.codeQR?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = stringResource(R.string.accesibility_QR)
                    )
                }
            }

            KkBody(
                modifier = Modifier.constrainAs(playersLabel) {
                    top.linkTo(qrBox.bottom, 20.dp)
                    start.linkTo(parent.start, 25.dp)
                }, label = stringResource(R.string.stringPlayers)
            )

        Box(modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .constrainAs(chipGroup) {
                top.linkTo(playersLabel.bottom, 20.dp)
            }
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                content = {
                    items(uiState.players) { player ->
                        KkChip(label = player.name)
                    }
                }
            )
        }

            KkButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .height(50.dp)
                    .constrainAs(startButton) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onClick = {waitingRoomAdminViewModel.handleEvent(WaitingRoomAdminContract.Event.OnStartGame)},
                label = stringResource(R.string.stringButton)
            )
        }
    }

    LaunchedEffect(key1 = Unit){
        waitingRoomAdminViewModel.effect.collectLatest {
            when(it){
                WaitingRoomAdminContract.Effect.Navigate -> navigateToStartGameHost.invoke()
            }
        }
    }
}


