package com.kk.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kk.designsystem.theme.KkTypography

@Composable
fun KkChip(
    label: String,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f))
            .padding(15.dp, 5.dp, 15.dp, 5.dp)
    ){
        Text(text = label, style = KkTypography.titleSmall)
    }
}