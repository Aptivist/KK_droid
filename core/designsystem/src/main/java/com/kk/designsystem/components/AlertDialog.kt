package com.kk.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.window.DialogProperties

@Composable
fun KKAlertDialog(
    visible: Boolean,
    title: String,
    message: String,
    onCancel: (() -> Unit)? = null,
    onConfirm: (() -> Unit),
    onDismiss: (() -> Unit)? = null,
    textCancelButton: String = "",
    textConfirmButton: String
) {
    AnimatedVisibility(visible = visible) {
        AlertDialog(
            onDismissRequest = { onDismiss?.invoke() },
            properties = DialogProperties(dismissOnBackPress = false),
            confirmButton = {
                KkButton(onClick = { onConfirm() }, label = textConfirmButton)
            },
            dismissButton = {
                if (onCancel != null){
                    KkButton(onClick = { onCancel() }, label = textCancelButton)
                }
            },

            title = { KkTitle(label = title) },
            text = { KkBody(label = message) }
        )
    }
}
