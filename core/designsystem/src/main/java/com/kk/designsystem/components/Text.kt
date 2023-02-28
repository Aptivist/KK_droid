package com.kk.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kk.designsystem.theme.DimGray
import com.kk.designsystem.theme.EerieBlack
import com.kk.designsystem.theme.KkTypography
import java.util.*

@Composable
fun KkTitle(
    modifier: Modifier = Modifier,
    label: String,
    textAlign: TextAlign? = TextAlign.Center
) {
    Text(
        text = label,
        style = KkTypography.titleMedium,
        modifier = modifier, textAlign = textAlign)
}

@Composable
fun KkTitleResult(
    modifier: Modifier = Modifier,
    label: String,
) {
    Text(
        text = label,
        style = KkTypography.titleMedium,
        color = EerieBlack,
        modifier = modifier)
}


@Composable
fun KkTitleLarge(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label,
        style = KkTypography.titleLarge,
        modifier = modifier.clickable { onClick?.invoke() })
}

@Composable
fun KkBodyGray(
    modifier: Modifier = Modifier,
    label: String,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = label,
        color = DimGray,
        style = KkTypography.bodyLarge,
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