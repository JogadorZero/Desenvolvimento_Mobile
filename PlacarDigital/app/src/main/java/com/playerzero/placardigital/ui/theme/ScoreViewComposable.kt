package com.playerzero.placardigital.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamScore(score: Score) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = score.teamName
        )
        Button(onClick = { score.teamScore += 1 }) {
            Text(text = "+")
        }
        Text(
            modifier = Modifier.padding(16.dp),
            text = "${score.teamScore}"
        )
        Button(onClick = {
            score.teamScore -= 1
        }) {
            Text(text = "-")
        }
    }
}

@Composable
fun ScoreView(team1: Score, team2: Score) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TeamScore(team1)
            TeamScore(team2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestScoreView() {
    val team1 = Score("Kotlin", 0)
    val team2 = Score("Java", 0)
    ScoreView(team1, team2)
}