package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import com.example.ui.PakLawViewModel
import com.example.ui.screens.MainAppScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: PakLawViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            com.google.firebase.FirebaseApp.initializeApp(this)
        } catch (e: Throwable) {
            android.util.Log.e("MainActivity", "FirebaseApp initialization failed on startup: ${e.message}")
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemTheme = isSystemInDarkTheme()
            var useDarkTheme by remember { mutableStateOf(systemTheme) }

            MyApplicationTheme(darkTheme = useDarkTheme) {
                MainAppScreen(
                    viewModel = viewModel,
                    useDarkTheme = useDarkTheme,
                    onThemeToggle = { useDarkTheme = it }
                )
            }
        }
    }
}
