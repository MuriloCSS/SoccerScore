package br.edu.ifsp.scl.sc3038467.soccerscore.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetupScreen(
    onNavigateToSummary: (String, String, Int, Int) -> Unit
) {

    var teamAName by rememberSaveable { mutableStateOf("") }
    var teamBName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuração da Partida", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = teamAName,
            onValueChange = { teamAName = it },
            label = { Text("Nome do Time A") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = teamBName,
            onValueChange = { teamBName = it },
            label = { Text("Nome do Time B") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
    }
}