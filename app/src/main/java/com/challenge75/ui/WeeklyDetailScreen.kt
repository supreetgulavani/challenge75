package com.challenge75.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge75.data.LocalStore
import com.challenge75.data.Task

@Composable
fun WeeklyDetailScreen(context: Context, week: Int, onBack: () -> Unit) {
    val store = LocalStore(context)
    var tasks by remember { mutableStateOf(store.getWeekly(week).toMutableStateList()) }
    var newGoal by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("Week $week", style = MaterialTheme.typography.h6)
            Button(onClick = {
                store.saveWeekly(week, tasks)
                onBack()
            }) { Text("Back") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(tasks) { index, task ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = task.isDone, onCheckedChange = {
                        tasks[index] = task.copy(isDone = it)
                        store.saveWeekly(week, tasks)
                    })
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(60.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            OutlinedTextField(
                value = newGoal,
                onValueChange = { newGoal = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Add weekly goal...") }
            )
            Button(onClick = {
                if (newGoal.isNotBlank()) {
                    val t = Task(title = newGoal)
                    tasks.add(t)
                    store.saveWeekly(week, tasks)
                    newGoal = ""
                }
            }) {
                Text("Add") 
            }
        }
    }
}
