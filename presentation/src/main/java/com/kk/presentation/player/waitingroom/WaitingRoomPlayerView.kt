package com.kk.presentation.player.waitingroom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kk.designsystem.components.KKBox
import com.kk.designsystem.components.KkBody
import com.kk.designsystem.components.KkChip
import com.kk.designsystem.components.KkTitle
import com.kk.presentation.R
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun WaitingRoomPlayerView(
    navigateToHome : () -> Unit,
    navigateToStartGamePlayer : () -> Unit,
    waitingRoomViewModel: WaitingRoomViewModel = koinViewModel()
) {
    val uiState = waitingRoomViewModel.uiState.collectAsState()

    KKBox(onClickConfirm = {waitingRoomViewModel.handleEvent(WaitingRoomContract.Event.CloseSession)}){
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (titleLabel, subtitleLabel, chipGroup) = createRefs()

            KkTitle(modifier = Modifier.constrainAs(titleLabel) {
                top.linkTo(parent.top, 25.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, label = stringResource(R.string.waiting_room_title))

            KkBody(modifier = Modifier.constrainAs(subtitleLabel) {
                top.linkTo(titleLabel.bottom, 20.dp)
                start.linkTo(parent.start, 25.dp)
            }, label = stringResource(R.string.waiting_room_subtitle))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .constrainAs(chipGroup) {
                        top.linkTo(subtitleLabel.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start,20.dp)
                        end.linkTo(parent.end,20.dp)
                    },

                content = {
                    items(uiState.value.playerList){ player ->
                        KkChip(label = player.name)
                    }
                }
            )
        }
    }

    LaunchedEffect(key1 = Unit){
        waitingRoomViewModel.effect.collectLatest {
            when(it){
                WaitingRoomContract.Effect.Navigate -> navigateToStartGamePlayer()
                WaitingRoomContract.Effect.NavigateToHome -> navigateToHome()
            }
        }
    }
}