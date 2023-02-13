package com.kk.designsystem.components

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.kk.designsystem.R


/**
 * Only use on the main view,
 * this component has a default loading if necessary and sends an alert dialog when the user presses the onBack button.
 */

@Composable
fun KKBox(
    isLoading: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {

    var showDialog by remember {
        mutableStateOf(false)
    }
    val activity = (LocalContext.current as Activity)
    BackHandler { showDialog = true }

    KKAlertDialog(
        visible = showDialog,
        title = stringResource(id = R.string.title_on_back_pressed),
        message = stringResource(id = R.string.message_on_back_pressed),
        onCancel = { showDialog = false },
        textCancelButton = stringResource(id = R.string.cancel_button_on_back_pressed),
        onConfirm = {
            showDialog = false
            activity.finish()
        },
        textConfirmButton = stringResource(id = R.string.confirm_button_on_back_pressed)
    )
    Box(modifier = Modifier.fillMaxSize(), content = content)
    KKLoadingView(isLoading = isLoading)

}