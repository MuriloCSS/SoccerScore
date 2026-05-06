package br.edu.ifsp.scl.sc3038467.soccerscore.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetupScreen(
    onNavigateToSummary: (String, String, Int, Int) -> Unit
) {
    // Gerenciamento de estado utilizando rememberSaveable para preservar dados em rotações de tela.
    var teamAName by rememberSaveable { mutableStateOf("") }
    var teamBName by rememberSaveable { mutableStateOf("") }
    var teamAGoalsText by rememberSaveable { mutableStateOf("") }
    var teamBGoalsText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuração da Partida", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Campo para nome do Time A
        OutlinedTextField(
            value = teamAName,
            onValueChange = { teamAName = it },
            label = { Text("Nome do Time A") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo para nome do Time B
        OutlinedTextField(
            value = teamBName,
            onValueChange = { teamBName = it },
            label = { Text("Nome do Time B") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo para Gols do Time A com validação para aceitar apenas dígitos
        OutlinedTextField(
            value = teamAGoalsText,
            onValueChange = { if (it.all { char -> char.isDigit() }) teamAGoalsText = it },
            label = { Text("Gols do Time A") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo para Gols do Time B com validação para aceitar apenas dígitos
        OutlinedTextField(
            value = teamBGoalsText,
            onValueChange = { if (it.all { char -> char.isDigit() }) teamBGoalsText = it },
            label = { Text("Gols do Time B") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Botão de ação para validar e navegar
        Button(
            onClick = {
                // Conversão de String para Int com tratamento de nulo
                val goalsA = teamAGoalsText.toIntOrNull()
                val goalsB = teamBGoalsText.toIntOrNull()

                // Regra de validação: campos preenchidos e números válidos
                if (teamAName.isNotBlank() && teamBName.isNotBlank() && goalsA != null && goalsB != null) {
                    onNavigateToSummary(teamAName, teamBName, goalsA, goalsB)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Resultado")
        }
    }
}

@Composable
fun SummaryScreen(
    teamA: String,
    teamB: String,
    goalsA: Int,
    goalsB: Int,
    onConfirm: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Resumo da Partida", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Exibição formatada do placar conforme requisitos
        Text(
            text = "$teamA $goalsA x $goalsB $teamB",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Botão para confirmar e seguir para a lógica de vencedor
        Button(
            onClick = onConfirm,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Confirmar Resultado")
        }

        // Botão para voltar e editar os dados
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Editar")
        }
    }
}


@Composable
fun ResultScreen(
    teamA: String,
    teamB: String,
    goalsA: Int,
    goalsB: Int,
    onRestart: () -> Unit
) {
    // Lógica de negócio: definição do veredito da partida
    val resultText = when {
        goalsA > goalsB -> "🏆 $teamA venceu!"
        goalsB > goalsA -> "🏆 $teamB venceu!"
        else -> "🤝 Empate emocionante!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RESULTADO FINAL",
            fontSize = 28.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text(text = teamA, fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)

            Text(
                text = "  $goalsA - $goalsB  ",
                fontSize = 40.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Black,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(text = teamB, fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        }

        Text(
            text = resultText,
            fontSize = 24.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // Botão para iniciar um novo jogo do zero
        Button(
            onClick = onRestart,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Novo Jogo", fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
    }
}