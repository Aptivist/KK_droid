package com.kk.designsystem.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f),
            disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(6.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KkNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f),
            cursorColor = MaterialTheme.colorScheme.primary,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

