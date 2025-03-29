package com.playerzero.placardigital.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Score(
    teamName: String,
    teamScore: Int,
) {
    var teamName by mutableStateOf(teamName)
    var teamScore by mutableIntStateOf(teamScore)
}