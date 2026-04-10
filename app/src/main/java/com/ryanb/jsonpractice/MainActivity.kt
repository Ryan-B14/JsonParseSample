package com.ryanb.jsonpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.ryanb.jsonpractice.view.ui.composables.ContactScreen
import com.ryanb.jsonpractice.view.ui.theme.JsonPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JsonPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

