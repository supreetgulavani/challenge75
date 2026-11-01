package com.challenge75.ui

import android.content.Context
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.challenge75.data.LocalStore
import com.challenge75.data.Task
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.delay
@Composable
fun DailyCalendarScreen(context: Context, modifier: Modifier = Modifier) {
    val days = (1..75).toList()
    var selectedDay by remember { mutableStateOf<Int?>(null) }

    val store = LocalStore(context)
    var completed by remember { mutableStateOf(store.getCompletedDaysCount()) }

    LaunchedEffect(Unit) {
        completed = store.getCompletedDaysCount()
    }

    val progress = completed.toFloat() / 75f
    val quotes = listOf(
        "Small steps every day lead to big changes.",
        "Discipline beats motivation.",
        "We are what we repeatedly do. Excellence then is not an act but a habit.",
        "You're becoming stronger every day.",
        "Your only competition is yesterday’s you.",
        "The body achieves what the mind believes.",
        "The real workout starts when you want to stop.",
        "The secret of getting ahead is getting started.",
        "Confidence comes from discipline and training.",
        "The only bad workout is the one that didn’t happen.",
        "You can either suffer the pain of discipline or the pain of regret.",
        "Don’t dream of winning. Train for it!",
        "Whether you think you can, or you think you can’t. you are right",
        "Be Humble. Be Hungry. And always be the hardest worker in the room.",
        "A year from now you may wish you had started today.",
        "If you do not believe in yourself, no one will do it for you",
        "Our bodies are gardens, our wills are our gardeners",
        "She who is not courageous enough to take risks, will accomplish nothing in life.",
        "It’s supposed to be hard. If it wasn’t hard, everyone would do it.",
        "The only place where success comes before work is in the dictionary.",
        "Making excuses burns zero calories per hour.",
        "A goal is a wish. A standard holds you accountable.",
        "The hardest part is over. You showed up",
        "You can do it Sampii! Lessgooo!",
        "Be the Sampii you wish to be",
        "The difference between try and triumph is a little ‘umph.'"
    )
    val randomQuote by remember { mutableStateOf(quotes.random()) }

    // ⭐ If completed all 75 days → Celebration Screen
    if (completed == 75) {
        CompletedScreen {
            selectedDay = null
        }
        return
    }
    if (selectedDay != null) {
        DailyTaskScreen(context, selectedDay!!) {
            selectedDay = null
            completed = store.getCompletedDaysCount()
        }
    } else {
        Box(modifier = Modifier
            .fillMaxSize()
            .zIndex(10f)
        ) {

            LoveOverlay()
        }
        Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(8.dp))
           // Text("75-Day Soft Challenge")
            Text(
                text = "\"$randomQuote\"",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text("$completed / 75 days completed")
            Spacer(modifier = Modifier.height(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier.padding(12.dp)
            ) {
                items(days) { day ->
                   val isCompleted =  store.isDayCompleted(day)
                    Card(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(72.dp)
                            .clickable { selectedDay = day }
                    ) {
                        Box(

                            modifier = Modifier
                                .background(
                                    if(isCompleted)
                                        androidx.compose.ui.graphics.Color(0xFF4CAF50)
                                    else
                                        androidx.compose.ui.graphics.Color.White
                                ),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text("Day $day")
                        }
                    }
                }
            }
        }
    }
}
