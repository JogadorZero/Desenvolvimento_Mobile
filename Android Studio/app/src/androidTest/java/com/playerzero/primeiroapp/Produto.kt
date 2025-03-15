package com.playerzero.primeiroapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Produto(
    val id: Int,
    val nome: String,
    val preco: Double,
    var estoque: Int
) {
    fun exibirDetalhes() {
        println("Id: $id  Nome: $nome  Preço: R$$preco  Estoque: $estoque")
    }
}





    fun calcularTotal(): Double {
        return produtos.sumByDouble { it.preco }
    }

    fun getProdutos(): MutableList<Produto> = produtos
}

class Loja(val nome: String) {
    private val produtosDisponiveis: MutableList<Produto> = mutableListOf()

    fun adicionarProduto(produto: Produto) {
        produtosDisponiveis.add(produto)
    }

    fun listarProdutos() {
        println("Produtos disponíveis na loja $nome:")
        produtosDisponiveis.forEach { it.exibirDetalhes() }
    }

    fun finalizarCompra(cliente: Cliente, carrinho: CarrinhoDeCompras) {
        val totalCompra = carrinho.calcularTotal()
        if (cliente.saldo >= totalCompra) {
            val produtosNoCarrinho = carrinho.getProdutos()
            val estoqueValido = produtosNoCarrinho.all { produto ->
                val produtoNaLoja = produtosDisponiveis.find { it.id == produto.id }
                produtoNaLoja != null && produtoNaLoja.estoque >= produtosNoCarrinho.count { it.id == produto.id }
            }

            if (estoqueValido) {
                for (produtoCarrinho in produtosNoCarrinho) {
                    val produtoNaLoja = produtosDisponiveis.find { it.id == produtoCarrinho.id }
                    produtoNaLoja?.estoque = produtoNaLoja?.estoque?.minus(produtosNoCarrinho.count { it.id == produtoCarrinho.id }) ?: 0 // Usando o operador -= corretamente
                }
                cliente.saldo -= totalCompra
                println("Compra finalizada com sucesso! Total da compra: R$$totalCompra")
                println("Saldo restante de ${cliente.nome}: R$${cliente.saldo}")
                carrinho.getProdutos().clear()
            } else {
                println("Alguns produtos no carrinho não estão disponíveis em estoque suficiente.")
            }
        } else {
            println("Saldo insuficiente para realizar a compra.")
        }
    }
}





@Preview(showBackground = true)
@Composable
fun TestProduto(){
    val p = Produto(1, "mouse", 749.9, 10)
    p.ExibirDetalhes()
}

annotation class Composable
