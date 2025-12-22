package com.loopwish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.loopwish.design.LoopwishDesign
import com.loopwish.design.loopwishTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loopwishApp()
        }
    }
}

@Composable
fun loopwishApp() {
    loopwishTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val design = LoopwishDesign.fromAssets(LocalContext.current)
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Loopwish", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = design.tagline,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
