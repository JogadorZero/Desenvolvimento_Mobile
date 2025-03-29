package com.playerzero.placardigital.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                val team1 = Score("Kotlin", 0)
                val team2 = Score("Java", 0)
                ScoreView(team1, team2)
            }
        }
    }
}