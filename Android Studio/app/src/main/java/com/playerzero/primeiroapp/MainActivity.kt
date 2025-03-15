package com.playerzero.primeiroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.playerzero.primeiroapp.ui.theme.PrimeiroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val p = Produto(1, "mouse", 749.9, 10)

        setContent{
            Column{
                val produto1 = Produto(1, "Notebook", 4500.0, 5)
                val produto2 = Produto(2, "Mouse Gamer", 250.0, 10)

                val cliente = Cliente(1, "Ana", 5000.0)

                val carrinho = CarrinhoDeCompras()

                val loja = Loja("TechStore")
                loja.adicionarProduto(produto1)
                loja.adicionarProduto(produto2)

                loja.listarProdutos()

                carrinho.adicionarProduto(produto1, 1)
                carrinho.adicionarProduto(produto2, 2)

                carrinho.exibirCarrinho()

                loja.finalizarCompra(cliente, carrinho)
                p.ExibirDetalhes()
            }
        }
        
        
        enableEdgeToEdge()
        setContent {
            PrimeiroAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class Produto(i: Int, s: String, d: Double, i1: Int) {
    inner class ExibirDetalhes {

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrimeiroAppTheme {
        Greeting("Android")
    }
}