package com.kk.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.iteneum.core.ui.components.camera.GKCameraScannerView
import com.kk.designsystem.components.KKBox
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkButton
import com.kk.designsystem.components.KkChip
import com.kk.designsystem.components.KkOrangeTitle
import com.kk.designsystem.components.KkTitle
import com.kk.designsystem.theme.BurntSienna
import com.kk.presentation.host.waitingroomadmin.WaitingRoomAdminViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WaitingRoomAdminView(
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
            }, label = "XAWFF")

            KkBody(modifier = Modifier.constrainAs(qrLabel) {
                top.linkTo(codeLabel.bottom, 20.dp)
                start.linkTo(parent.start, 25.dp)
            }, label = stringResource(R.string.stringQR))
            //QR
            Card(
                modifier = Modifier
                    .height(230.dp)
                    .width(230.dp)
                    .background(color = Color.White)
                    .border(2.dp, BurntSienna, RoundedCornerShape(5.dp))
                    .constrainAs(qrBox) {
                        top.linkTo(qrLabel.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                shape = RoundedCornerShape(5.dp)
            ) {
GKCameraScannerView(modifier = Modifier.fillMaxSize()){

}

            }

            KkBody(
                modifier = Modifier.constrainAs(playersLabel) {
                    top.linkTo(qrBox.bottom, 20.dp)
                    start.linkTo(parent.start, 25.dp)
                }, label = stringResource(R.string.stringPlayers)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .constrainAs(chipGroup) {
                        top.linkTo(playersLabel.bottom, 20.dp)
                        bottom.linkTo(startButton.top)
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(parent.end, 20.dp)
                    },

                content = {
                    items(uiState.players) { player ->
                        KkChip(label = player.name)
                    }
                }
            )

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
                onClick = { navigateToStartGameHost.invoke() },
                label = stringResource(R.string.stringButton)
            )
        }
    }
}


