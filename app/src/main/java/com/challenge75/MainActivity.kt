/*
package com.challenge75


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.challenge75.ui.DailyCalendarScreen
import com.challenge75.ui.WeeklyScreen
import com.challenge75.notifications.NotificationUtils
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.challenge75.ui.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationUtils.createNotificationChannel(this)

        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = "splash") {
                composable("splash") { WelcomeScreen(nav) }
                composable("home") { DailyCalendarScreen(this ) }
            }

            var tab by remember { mutableStateOf(0) }

            MaterialTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            BottomNavigationItem(
                                selected = tab == 0,
                                onClick = { tab = 0 },
                                icon = { /* you can add icon */ },
                                label = { Text("Daily") }
                            )
                            BottomNavigationItem(
                                selected = tab == 1,
                                onClick = { tab = 1 },
                                icon = { /* icon */ },
                                label = { Text("Weekly") }
                            )
                        }
                    }
                ) { innerPadding ->
                    when (tab) {
                        0 -> DailyCalendarScreen(this, Modifier.padding(innerPadding))
                        1 -> WeeklyScreen(this, Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
*/

package com.challenge75

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.challenge75.notifications.NotificationUtils
import com.challenge75.ui.WelcomeScreen
import com.challenge75.ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationUtils.createNotificationChannel(this)

        setContent {
            val nav = rememberNavController()
            val activityContext = this

            NavHost(navController = nav, startDestination = "splash") {

                // Splash page
                composable("splash") {
                    WelcomeScreen(
                        navController = nav
                    )
                }

                // Main Tabs page
                composable("home") {
                    HomeScreen(context = activityContext)
                }
            }
        }
    }
}

