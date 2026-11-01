package com.challenge75.data

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    var isDone: Boolean = false
)
