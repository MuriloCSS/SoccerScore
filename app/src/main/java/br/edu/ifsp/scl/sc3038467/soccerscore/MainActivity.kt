package br.edu.ifsp.scl.sc3038467.soccerscore

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
import br.edu.ifsp.scl.sc3038467.soccerscore.ui.AppNavigation
import br.edu.ifsp.scl.sc3038467.soccerscore.ui.theme.SoccerScoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita o layout ocupando toda a tela
        enableEdgeToEdge()
        setContent {
            SoccerScoreTheme() {
                // Scaffold gerencia a estrutura base e as margens do sistema
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    androidx.compose.foundation.layout.Box(modifier = Modifier.padding(innerPadding)) {
                        // Inicia o fluxo de navegação do app
                        AppNavigation()
                    }
                }
            }
        }
    }
}