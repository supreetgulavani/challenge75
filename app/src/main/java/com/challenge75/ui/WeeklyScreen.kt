package com.challenge75.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge75.data.LocalStore

@Composable
fun WeeklyScreen(context: Context, modifier: Modifier = Modifier) {
    val weeks = (1..11).toList()
    val store = LocalStore(context)
    var selectedWeek by remember { mutableStateOf<Int?>(null) }

    if (selectedWeek != null) {
        WeeklyDetailScreen(context, selectedWeek!!) {
            selectedWeek = null
        }
    } else {
        LazyColumn(modifier = modifier.padding(12.dp)) {
            items(weeks) { week ->
                val isCompleted =  store.isWeekCompleted(week)
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedWeek = week }
                    .padding(16.dp)
                ) {
                    Box(

                        modifier = Modifier
                            .background(
                                if (isCompleted)
                                    androidx.compose.ui.graphics.Color(0xFF4CAF50)
                                else
                                    androidx.compose.ui.graphics.Color.White
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text("Week $week")
                    }
                }
            }
        }
    }
}
