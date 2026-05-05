package br.edu.ifsp.scl.sc3038467.soccerscore.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


sealed class Screen(val route: String) {
    // Rota inicial sem parâmetros
    object Setup : Screen("setup")

    // Rota de resumo que recebe os dados da partida
    object Summary : Screen("summary/{teamA}/{teamB}/{goalsA}/{goalsB}") {
        fun createRoute(teamA: String, teamB: String, goalsA: Int, goalsB: Int) =
            "summary/$teamA/$teamB/$goalsA/$goalsB"
    }

    // Rota de resultado final que recebe os dados para cálculo e exibição
    object Result : Screen("result/{teamA}/{teamB}/{goalsA}/{goalsB}") {
        fun createRoute(teamA: String, teamB: String, goalsA: Int, goalsB: Int) =
            "result/$teamA/$teamB/$goalsA/$goalsB"
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Setup.route) {
        // Configuração da Tela 1: Configuração da Partida
        composable(Screen.Setup.route) {
            SetupScreen(
                onNavigateToSummary = { teamA, teamB, goalsA, goalsB ->
                    navController.navigate(Screen.Summary.createRoute(teamA, teamB, goalsA, goalsB))
                }
            )
        }
        // Configuração da Tela 2: Resumo da Partida com definição de argumentos
        composable(
            route = Screen.Summary.route,
            arguments = listOf(
                navArgument("teamA") { type = NavType.StringType },
                navArgument("teamB") { type = NavType.StringType },
                navArgument("goalsA") { type = NavType.IntType },
                navArgument("goalsB") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            // Extração dos argumentos passados pela rota
            val teamA = backStackEntry.arguments?.getString("teamA") ?: ""
            val teamB = backStackEntry.arguments?.getString("teamB") ?: ""
            val goalsA = backStackEntry.arguments?.getInt("goalsA") ?: 0
            val goalsB = backStackEntry.arguments?.getInt("goalsB") ?: 0

            SummaryScreen(
                teamA = teamA,
                teamB = teamB,
                goalsA = goalsA,
                goalsB = goalsB,
                onConfirm = {
                    navController.navigate(Screen.Result.createRoute(teamA, teamB, goalsA, goalsB))
                },
                onBack = {
                    // Retorna para a tela anterior mantendo o estado
                    navController.popBackStack()
                }
            )
        }
        // Configuração da Tela 3: Resultado Final
        composable(
            route = Screen.Result.route,
            arguments = listOf(
                navArgument("teamA") { type = NavType.StringType },
                navArgument("teamB") { type = NavType.StringType },
                navArgument("goalsA") { type = NavType.IntType },
                navArgument("goalsB") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val teamA = backStackEntry.arguments?.getString("teamA") ?: ""
            val teamB = backStackEntry.arguments?.getString("teamB") ?: ""
            val goalsA = backStackEntry.arguments?.getInt("goalsA") ?: 0
            val goalsB = backStackEntry.arguments?.getInt("goalsB") ?: 0

            ResultScreen(
                teamA = teamA,
                teamB = teamB,
                goalsA = goalsA,
                goalsB = goalsB,
                onRestart = {
                    // Navega de volta para a tela inicial limpando toda a pilha
                    navController.navigate(Screen.Setup.route) {
                        popUpTo(Screen.Setup.route) { inclusive = true }
                    }
                }
            )
        }
    }
}