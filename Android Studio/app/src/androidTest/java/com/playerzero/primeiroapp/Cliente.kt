package com.playerzero.primeiroapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Cliente(
    val id: Int,
    val nome: String,
    var saldo: Double
) {
    @Composable
    fun adicionarSaldo(valor: Double) {
        if (valor >= 0) {
            saldo += valor
            Text(text = "$nome agora tem R$$saldo de saldo.")
        } else {
            Text(text = "Valor inválido para adicionar ao saldo.")
        }
    }
}