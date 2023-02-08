package com.kk.knowledgeknockout

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.kk.designsystem.components.*
import com.kk.designsystem.theme.KnowledgeKnockoutTheme
import com.kk.presentation.ScreenHome

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KnowledgeKnockoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayComponent()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}

@Composable
fun DisplayComponent() {
    val rememberText = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        KkButton(onClick = { /*TODO*/ }, label = "Crear partida")
        Spacer(modifier = Modifier.height(5.dp))

        KkCorrectButton(onClick = { /*TODO*/ }, label = "Correcto")
        Spacer(modifier = Modifier.height(5.dp))

        KkIncorrectButton(onClick = { /*TODO*/ }, label = "Incorrecto")
        Spacer(modifier = Modifier.height(5.dp))

        KkTitle(label = "1ra Ronda")
        Spacer(modifier = Modifier.height(5.dp))

        KkBody(label = "Haz tu pregunta...")
        Spacer(modifier = Modifier.height(5.dp))

        KkOrangeTitle(label = "Comenzar ronda")
        Spacer(modifier = Modifier.height(5.dp))

        KkCorrectTitle(label = "¡Respuesta correcta!")
        Spacer(modifier = Modifier.height(5.dp))

        KkIncorrectTitle(label = "¡Punto para gciadiego!")
        Spacer(modifier = Modifier.height(5.dp))

        KkBody(label = "Nombre")
        KkTextField(
            value = rememberText.value,
            onValueChange = { rememberText.value = it },
        )
        Spacer(modifier = Modifier.height(5.dp))

        KkBody(label = "Participantes")
        KkNumberField(
            value = rememberText.value,
            onValueChange = { rememberText.value = it },
        )
        Spacer(modifier = Modifier.height(5.dp))

        KkChip(
            label = "Assist Chip"
        )
    }
}