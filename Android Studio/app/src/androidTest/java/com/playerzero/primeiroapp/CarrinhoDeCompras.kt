package com.playerzero.primeiroapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class CarrinhoDeCompras {
    val produtos: MutableList<Produto> = mutableListOf()

    @Composable
    fun adicionarProduto(produto: Produto, quantidade: Int) {
        if (quantidade > 0 && produto.estoque >= quantidade) {
            for (i in 1..quantidade) {
                produtos.add(produto)
            }
            produto.estoque -= quantidade
            Text(text = "Adicionado $quantidade ${produto.nome}(s) ao carrinho.")
        } else {
            Text(text = "Estoque insuficiente ou quantidade inválida para o produto ${produto.nome}.")
        }
    }

    @Composable
    fun removerProduto(produto: Produto) {
        val removed = produtos.filter { it.id == produto.id }
        if (removed.isNotEmpty()) {
            produtos.removeAll(removed)
            produto.estoque += removed.size
            Text(text = "Removido ${removed.size} ${produto.nome}(s) do carrinho.")
        } else {
            Text(text = "${produto.nome} não encontrado no carrinho.")
        }
    }

    @Composable
    fun exibirCarrinho() {
        if (produtos.isEmpty()) {
            println("O carrinho está vazio.")
        } else {
            Text(text = "Itens no carrinho:")
            produtos.groupBy { it.nome }.forEach { (nome, lista) ->
                Text(text = "$nome - Quantidade: ${lista.size}  Preço unitário: R$${lista[0].preco}")
            }
        }
    }