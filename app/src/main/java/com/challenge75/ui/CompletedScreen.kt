package com.challenge75.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
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
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.*
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun CompletedScreen(onBack: () -> Unit) {

    // 🎖 MEDAL DROP ANIMATION
    val dropAnim = remember { Animatable(-500f) }   // start above the screen
    val bounceAnim = remember { Animatable(1f) }    // scale animation

    LaunchedEffect(true) {
        dropAnim.animateTo(
            targetValue = 0f,
            animationSpec = tween(1200, easing = LinearOutSlowInEasing)
        )

        bounceAnim.animateTo(
            targetValue = 1.15f,
            animationSpec = tween(300)
        )
        bounceAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(250)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        // 🎉 KONFETTI
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(
                    speed = 0f,
                    maxSpeed = 35f,
                    angle = 270,
                    spread = 90,
                    emitter = Emitter(duration = 3, TimeUnit.SECONDS).perSecond(250)
                )
            )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            // 🏅 Animated Medal
            Text(
                text = "🏅",
                fontSize = 100.sp,
                modifier = Modifier.graphicsLayer(
                    translationY = dropAnim.value,
                    scaleX = bounceAnim.value,
                    scaleY = bounceAnim.value
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "75-Day Challenge Completed!",
                style = MaterialTheme.typography.h4,
                color = Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "You didn’t just finish a challenge.\nYou built discipline. You became stronger.\nI am so proud of you bubs!",
                style = MaterialTheme.typography.subtitle1,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🔙 Back Button
            Surface(
                shape = CircleShape,
                color = Color(0xFF4CAF50),
                modifier = Modifier
                    .padding(12.dp)
                    .clickable{ onBack() }
            ) {
                Box(
                    Modifier.padding(horizontal = 26.dp, vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Back", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}
