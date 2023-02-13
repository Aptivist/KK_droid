package com.kk.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kk.designsystem.theme.KkTypography
import java.util.*

@Composable
fun KkTitle(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label,
        style = KkTypography.titleMedium,
        modifier = modifier.clickable { onClick?.invoke() })
}

@Composable
fun KkBody(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label,
        style = KkTypography.bodyLarge,
        modifier = modifier.clickable { onClick?.invoke() })
}

@Composable
fun KkOrangeTitle(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label.uppercase(Locale.ROOT),
        color = MaterialTheme.colorScheme.onPrimary,
        style = KkTypography.titleLarge,
        modifier = modifier.clickable { onClick?.invoke() }
    )
}

@Composable
fun KkCorrectTitle(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label.uppercase(Locale.ROOT),
        color = MaterialTheme.colorScheme.tertiary,
        style = KkTypography.titleLarge,
        modifier = modifier.clickable { onClick?.invoke() }
    )
}

@Composable
fun KkIncorrectTitle(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label.uppercase(Locale.ROOT),
        color = MaterialTheme.colorScheme.error,
        style = KkTypography.titleLarge,
        modifier = modifier.clickable { onClick?.invoke() }
    )
}