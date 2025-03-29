package com.playerzero.cacaaotesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.playerzero.cacaaotesouro.ui.theme.CacaAoTesouroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val meuNavController = rememberNavController()
            CacaAoTesouroTheme {
                NavHost(
                    navController = meuNavController,
                    startDestination = "/home"
                ) {
                    composable("/home") {
                        Tela(
                            nomeTela = "Home",
                            corFundo = Color.Yellow,
                            clickB1 = { meuNavController.navigate("/tela01") },
                            clickB2 = { meuNavController.navigate("/tela02") }
                        )
                    }
                    composable("/tela01") {
                        Tela(
                            nomeTela = "Tela 01",
                            corFundo = Color.Red,
                            clickB1 = { meuNavController.navigate("/tela02") },
                            clickB2 = { meuNavController.navigate("/tela03") }
                        )
                    }
                    composable("/tela02") {
                        Tela(
                            nomeTela = "Tela 02",
                            corFundo = Color.Green,
                            clickB1 = { meuNavController.navigate("/tela03") },
                            clickB2 = { meuNavController.navigate("/home") }
                        )
                    }
                    composable("/tela03") {
                        Tela(
                            nomeTela = "Tela 03",
                            corFundo = Color.Blue,
                            clickB1 = { meuNavController.navigate("/home") },
                            clickB2 = { meuNavController.navigate("/tela01") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Tela(
    nomeTela: String,
    corFundo: Color,
    clickB1: () -> Unit,
    clickB2: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corFundo)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(nomeTela, color = Color.White)
        Button(onClick = clickB1) { Text("B1") }
        Button(onClick = clickB2) { Text("B2") }
    }
}

@Preview
@Composable
fun Teste() {
    Tela(
        nomeTela = "Tela de teste",
        corFundo = Color.Gray,
        clickB1 = {},
        clickB2 = {}
    )
}
