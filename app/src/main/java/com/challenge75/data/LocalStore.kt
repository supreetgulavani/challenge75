package com.challenge75.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalStore(private val context: Context) {

    private val prefs = context.getSharedPreferences("Sampi's 75-Day Soft", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveDaily(day: Int, tasks: List<Task>) {
        prefs.edit().putString("daily_$day", gson.toJson(tasks)).apply()
    }

    fun getDaily(day: Int): List<Task> {
        val json = prefs.getString("daily_$day", null)
        return if (json == null){
            listOf(
                Task("1", "Have Vitamins", false),
                Task("2", "Use Toe Corrector", false),
                Task("3", "Workout", false),
                Task("4", "Cook Meal", false),
                Task("5", "Read a book", false),
                Task("6", "Improve a skill", false)
            )
        } else {
            val type = object : TypeToken<List<Task>>() {}.type
            gson.fromJson(json, type)
        }
    }

    fun saveWeekly(week: Int, tasks: List<Task>) {
        prefs.edit().putString("weekly_$week", gson.toJson(tasks)).apply()
    }

    fun getWeekly(week: Int): List<Task> {
        val json = prefs.getString("weekly_$week", null)
        return if(json == null){
            listOf(
                Task("1", "At least 2 workouts", false),
                Task("2", "5 home-cooked meals", false),
                Task("3", "1 Creative Session", false),
                Task("4", "Book of the week", false),
                Task("5", "Job-related skill building", false)
            )
        } else {
            val type = object : TypeToken<List<Task>>() {}.type
            gson.fromJson(json, type)
        }

    }

    fun getCompletedDaysCount(): Int {
        var count = 0
        for (day in 1..75) {
            val tasks = getDaily(day)
            if (tasks.isNotEmpty() && tasks.all { it.isDone }) count++
        }
        return count
    }
    fun isDayCompleted(day: Int): Boolean {
        val tasks = getDaily(day)
        return tasks.isNotEmpty() && tasks.all { it.isDone }
    }
    fun isWeekCompleted(week: Int): Boolean {
        val tasks = getWeekly(week)
        return tasks.isNotEmpty() && tasks.all { it.isDone }
    }
}
