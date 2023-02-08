package com.kk.knowledgeknockout

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.kk.designsystem.components.*
import com.kk.designsystem.theme.KnowledgeKnockoutTheme

class MainActivity : ComponentActivity() {

    private val viewModel : ViewModelTest by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = viewModel.uiState.collectAsState()
            KnowledgeKnockoutTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        KkButton(onClick = { viewModel.setEvent(ContractTest.Event.OnRandomNumberClicked)}, label = "MVI")

                        if (uiState.value.randomNumberState is ContractTest.RandomNumberState.Success){
                            KkTitle(label = "Success ${(uiState.value.randomNumberState as ContractTest.RandomNumberState.Success).value}")
                        }
                        if (uiState.value.randomNumberState is ContractTest.RandomNumberState.Loading){
                            KkTitle(label = "Loading")
                        }
                    }
                }
            }
        }
        hideStatusBar()
    }

    private fun hideStatusBar() {
        actionBar?.hide()
        //Hide the status bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsetsCompat.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}