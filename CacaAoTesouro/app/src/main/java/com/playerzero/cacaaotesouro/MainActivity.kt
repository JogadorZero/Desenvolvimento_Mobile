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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            var score by remember { mutableStateOf(0) }

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    TelaHome {
                        score = 0
                        navController.navigate("tela1")
                    }
                }
                composable("tela1") {
                    TelaJogo(
                        nomeTela = "T1: Vila",
                        botoes = listOf(
                            "Entrar furtivamente pela lateral" to true,
                            "Atirar nos inimigos de frente" to false,
                            "Pular no meio da vila sem plano" to false
                        ),
                        navController = navController,
                        score = score,
                        onScoreChange = { score = it },
                        proximaTela = "tela2"
                    )
                }
                composable("tela2") {
                    TelaJogo(
                        nomeTela = "T2: Casa Misteriosa",
                        botoes = listOf(
                            "Investigar os sons antes de entrar" to true,
                            "Abrir a porta sem verificar" to false,
                            "Chutar a porta com força" to false
                        ),
                        navController = navController,
                        score = score,
                        onScoreChange = { score = it },
                        proximaTela = "tela3"
                    )
                }
                composable("tela3") {
                    TelaJogo(
                        nomeTela = "T3: Travessia",
                        botoes = listOf(
                            "Seguir pelas cavernas" to true,
                            "Passar pela ponte principal" to false,
                            "Tentar nadar pelo rio" to false
                        ),
                        navController = navController,
                        score = score,
                        onScoreChange = { score = it },
                        proximaTela = "tela4"
                    )
                }
                composable("tela4") {
                    TelaJogo(
                        nomeTela = "T4: Castelo",
                        botoes = listOf(
                            "Usar os túneis subterrâneos" to true,
                            "Seguir pelos corredores iluminados" to false,
                            "Escalar uma parede" to true
                        ),
                        navController = navController,
                        score = score,
                        onScoreChange = { score = it },
                        proximaTela = "tela5"
                    )
                }
                composable("tela5") {
                    TelaJogo(
                        nomeTela = "T5: Encontro Final",
                        botoes = listOf(
                            "Esperar e observar antes de agir" to true,
                            "Avançar sem cautela" to false,
                            "Atacar tudo que vê" to false
                        ),
                        navController = navController,
                        score = score,
                        onScoreChange = { score = it },
                        proximaTela = "final"
                    )
                }
                composable("final") {
                    when {
                        score == 5 -> TelaFinal("Leon achou Ashley Viva!", Color.Green)
                        score >= 3 -> TelaFinal("Leon achou Ashley Morta!", Color.DarkGray)
                        else -> TelaFinal("Leon Morreu!", Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun TelaHome(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "RE4 - Ache Ashley", fontSize = 24.sp, color = Color.Black)
        Button(onClick = onStartClick) {
            Text("Iniciar")
        }
    }
}

@Composable
fun TelaJogo(
    nomeTela: String,
    botoes: List<Pair<String, Boolean>>,
    navController: androidx.navigation.NavController,
    score: Int,
    onScoreChange: (Int) -> Unit,
    proximaTela: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = nomeTela, fontSize = 20.sp, color = Color.White)

        botoes.forEach { (texto, isCerto) ->
            Button(
                onClick = {
                    val novoScore = if (isCerto) score + 1 else score
                    onScoreChange(novoScore)
                    navController.navigate(proximaTela)
                }
            ) {
                Text(texto)
            }
        }
    }
}

@Composable
fun TelaFinal(mensagem: String, corFundo: Color) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corFundo)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = mensagem, fontSize = 24.sp, color = Color.White)
    }
}

@Preview
@Composable
fun PreviewHome() {
    TelaHome(onStartClick = {})
}
