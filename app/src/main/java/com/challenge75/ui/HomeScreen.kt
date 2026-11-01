package com.challenge75.ui

import android.content.Context
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun HomeScreen(context: Context) {
    var tab by remember { mutableStateOf(0) }

/*
    fun ConfettiLayer() {
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(
                    speed = 20f,
                    maxSpeed = 40f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(0xFFF44336, 0xFFFFC107, 0xFF4CAF50).map { Color(it.toInt()) },
                    emitter = Emitter(duration = 800, TimeUnit.MILLISECONDS).max(200)
                )
            )
        )
    }
*/
    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    BottomNavigationItem(
                        selected = tab == 0,
                        onClick = { tab = 0 },
                        icon = {},
                        label = { Text("Daily") }
                    )
                    BottomNavigationItem(
                        selected = tab == 1,
                        onClick = { tab = 1 },
                        icon = {},
                        label = { Text("Weekly") }
                    )
                }
            }
        ) { innerPadding ->

            when (tab) {
                0 -> DailyCalendarScreen(context, Modifier.padding(innerPadding))
                1 -> WeeklyScreen(context, Modifier.padding(innerPadding))
            }
        }
    }
}
