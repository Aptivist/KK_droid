package com.kk.knowledgeknockout

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.kk.data.SocketServicePlayerImp
import com.kk.data.model.*
import com.kk.designsystem.components.*
import com.kk.designsystem.theme.KnowledgeKnockoutTheme
import com.kk.presentation.ScreenCreateRoom
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val socketServicePlayer: SocketServicePlayerImp by inject()
    var data: StateFlow<BaseResult<String>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            socketServicePlayer.connectSocket()

            data = socketServicePlayer.receiveData().stateIn(
                scope = lifecycleScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = BaseResult(data = "", status = ""))
        }

        setContent {
            KnowledgeKnockoutTheme {
                val state = data?.collectAsState()

                Log.e("state", state.toString())

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //NavigationGraph()
                    ScreenCreateRoom(){
                        lifecycleScope.launch {
                            socketServicePlayer.requestSocket(
                                Gson().toJson(
                                    PlayerUser(
                                        "2",
                                        "diegobm",
                                        null,
                                        "1234"
                                    )

                                    /*CreateGameRequest(
                                        HostUser(null),
                                        Rules(5, 5, 45)
                                    )*/
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideStatusBar()
    }
    private fun hideStatusBar() {
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