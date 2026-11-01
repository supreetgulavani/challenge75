package com.challenge75.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun LoveOverlay() {

    // Stores active floating messages by unique id
    var loveMessages by remember { mutableStateOf(listOf<Int>()) }
    var loveCounter by remember { mutableStateOf(0) }

    // coroutine scope for launching timed removals from non-composable helper
    val scope = rememberCoroutineScope()

    // Function to spawn a message and auto-remove it after 2s
    fun spawnLoveMessage() {
        val id = loveCounter++
        loveMessages = loveMessages + id

        // launch a coroutine to remove after 2 seconds
        scope.launch {
            kotlinx.coroutines.delay(2000)
            loveMessages = loveMessages - id
        }
    }

    // Heart button pulse animation
    var tap by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (tap) 1.3f else 1f,
        animationSpec = tween(200, easing = LinearOutSlowInEasing),
        finishedListener = { tap = false }
    )

    Box(modifier = Modifier.fillMaxSize()) {

        // Top-right heart button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Surface(
                modifier = Modifier
                    .size(42.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale)
                    .clickable {
                        tap = true
                        spawnLoveMessage()
                    },
                shape = CircleShape,
                color = Color(0xFFFFC1CC)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("❤", fontSize = 20.sp)
                }
            }
        }

        // Floating messages: each message uses remember(id) to fix random offsets per message
        loveMessages.forEach { id ->

            // random offsets that remain stable for this id
            val startX = remember(id) { ( -180..180 ).random().dp }
            val startY = remember(id) { ( -100..100 ).random().dp }

            // animate vertical movement from 0 -> -200 over 2000ms
            val yProgress = remember { Animatable(0f) }
            LaunchedEffect(id) {
                yProgress.animateTo(
                    targetValue = -500f,
                    animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
                )
            }

            // animate alpha from 1 -> 0 over 2000ms
            val alphaProgress = remember { Animatable(1f) }
            LaunchedEffect(id) {
                alphaProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 2000)
                )
            }

            Text(
                text = "I love you ❤️",
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = startX, y = startY + yProgress.value.dp)
                    .graphicsLayer(alpha = alphaProgress.value),
                style = MaterialTheme.typography.h5
            )
        }
    }
}
