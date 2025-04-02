package com.playerzero.cacaaotesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
                        historia = "Leon chega à vila e vê vários Ganados armados. Ele precisa decidir como agir.",
                        botoes = listOf(
                            "Entrar furtivamente pela lateral" to true,
                            "Atirar nos inimigos de frente" to false,
                            "Pular no meio da vila sem plano" to false
                        ),
                        navController,
                        score,
                        { score = it },
                        "home", "tela2"
                    )
                }
                composable("tela2") {
                    TelaJogo(
                        nomeTela = "T2: Casa Misteriosa",
                        historia = "Leon encontra uma casa suspeita. Sons estranhos ecoam de dentro. O que fazer?",
                        botoes = listOf(
                            "Investigar os sons antes de entrar" to true,
                            "Abrir a porta sem verificar" to false,
                            "Chutar a porta com força" to false
                        ),
                        navController,
                        score,
                        { score = it },
                        "tela1", "tela3"
                    )
                }
                composable("tela3") {
                    TelaJogo(
                        nomeTela = "T3: Travessia",
                        historia = "Um caminho perigoso se apresenta. Ele precisa escolher a melhor rota.",
                        botoes = listOf(
                            "Seguir pelas cavernas" to true,
                            "Passar pela ponte principal" to false,
                            "Tentar nadar pelo rio" to false
                        ),
                        navController,
                        score,
                        { score = it },
                        "tela2", "tela4"
                    )
                }
                composable("tela4") {
                    TelaJogo(
                        nomeTela = "T4: Castelo",
                        historia = "Leon chega ao castelo e precisa decidir como se infiltrar sem ser notado.",
                        botoes = listOf(
                            "Usar os túneis subterrâneos" to true,
                            "Seguir pelos corredores iluminados" to false,
                            "Escalar uma parede" to true
                        ),
                        navController,
                        score,
                        { score = it },
                        "tela3", "tela5"
                    )
                }
                composable("tela5") {
                    TelaJogo(
                        nomeTela = "T5: Encontro Final",
                        historia = "Finalmente, Leon encontra o esconderijo de Ashley. Mas algo está errado...",
                        botoes = listOf(
                            "Esperar e observar antes de agir" to true,
                            "Avançar sem cautela" to false,
                            "Atacar tudo que vê" to false
                        ),
                        navController,
                        score,
                        { score = it },
                        "tela4", "final"
                    )
                }
                composable("final") {
                    when {
                        score == 5 -> TelaFinal("Leon achou Ashley Viva!", Color.Green, navController)
                        score >= 3 -> TelaFinal("Leon achou Ashley Morta!", Color.DarkGray, navController)
                        else -> TelaFinal("Leon Morreu!", Color.Red, navController)
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
    historia: String,
    botoes: List<Pair<String, Boolean>>,
    navController: androidx.navigation.NavController,
    score: Int,
    onScoreChange: (Int) -> Unit,
    telaAnterior: String,
    proximaTela: String
) {
    var mensagem by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = nomeTela, fontSize = 20.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = historia, fontSize = 16.sp, color = Color.LightGray)

        Spacer(modifier = Modifier.height(16.dp))

        botoes.forEach { (texto, isCerto) ->
            Button(
                onClick = {
                    mensagem = if (isCerto) {
                        onScoreChange(score + 1)
                        "Escolha Certa! ✅"
                    } else {
                        "Escolha Errada! ❌"
                    }
                },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(texto)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        mensagem?.let {
            Text(text = it, fontSize = 18.sp, color = if (it.contains("Certa")) Color.Green else Color.Red)
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = { navController.navigate(telaAnterior) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("Voltar")
                }

                Button(
                    onClick = { navController.navigate(proximaTela) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("Próximo")
                }
            }
        }
    }
}

@Composable
fun TelaFinal(mensagem: String, corFundo: Color, navController: androidx.navigation.NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(corFundo)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = mensagem, fontSize = 24.sp, color = Color.White)
        Button(onClick = { navController.navigate("home") }) {
            Text("Jogar Novamente")
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    TelaHome(onStartClick = {})
}
