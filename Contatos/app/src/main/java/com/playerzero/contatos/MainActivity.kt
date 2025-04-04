package com.playerzero.contatos

import android.R.attr.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.playerzero.contatos.ui.theme.ContatosTheme
import android.R.attr.name


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "NOME_BANCO"
        )
            .allowMainThreadQueries()
            .build()

        val userDao = db.userDao()

        // Inserção do usuário (corrigido)
        userDao.insertAllUser(User(
            userName = "Lucas",
            userPhone = "123456789"
        ))

        val users = userDao.getAll()

        setContent {
            ContatosTheme {
                Scaffold { padding ->
                    Text("Total de contatos: ${users.size}", modifier = Modifier.padding(padding))
                }
            }
        }
    }
}
