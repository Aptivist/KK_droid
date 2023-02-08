package com.kk.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kk.designsystem.theme.BurntSienna
import com.kk.designsystem.theme.KkTypography
import com.kk.designsystem.theme.RedSalsa
import com.kk.designsystem.theme.ShamrockGreen
import java.util.*

@Composable
fun KkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    border: BorderStroke = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = border,
        colors = colors,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text = label.uppercase(Locale.ROOT), style = KkTypography.titleSmall)
    }
}

@Composable
fun KkCorrectButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String
) {
    KkButton(
        onClick = { onClick() },
        label = label,
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.tertiary)
    )
}

@Composable
fun KkIncorrectButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String
) {
    KkButton(
        onClick = { onClick() },
        label = label,
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.error),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
    )
}