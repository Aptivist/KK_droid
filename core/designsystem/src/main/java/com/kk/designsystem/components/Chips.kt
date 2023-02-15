package com.kk.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            .padding(end = 5.dp, bottom = 5.dp)
    ){
        Box(
            modifier = modifier
                .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f))
                .padding(10.dp,5.dp,10.dp,5.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = label, style = KkTypography.titleSmall)
        }
    }
}