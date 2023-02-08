package com.kk.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kk.designsystem.theme.BurntSienna
import com.kk.designsystem.theme.KkTypography
import com.kk.designsystem.theme.RedSalsa
import com.kk.designsystem.theme.ShamrockGreen
import java.util.*

@Composable
fun KkTitle(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(text = label, style = KkTypography.titleMedium, modifier = modifier)
}

@Composable
fun KkBody(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(text = label, style = KkTypography.bodyLarge, modifier = modifier)
}

@Composable
fun KkOrangeTitle(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(
        text = label.uppercase(Locale.ROOT),
        color = MaterialTheme.colorScheme.onPrimary,
        style = KkTypography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun KkCorrectTitle(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(
        text = label.uppercase(Locale.ROOT),
        color = MaterialTheme.colorScheme.tertiary,
        style = KkTypography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun KkIncorrectTitle(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(
        text = label.uppercase(Locale.ROOT),
        color = MaterialTheme.colorScheme.error,
        style = KkTypography.titleLarge,
        modifier = modifier
    )
}