# Simulador de Placar de Futebol ⚽

Este é um aplicativo Android desenvolvido com **Kotlin** e **Jetpack Compose**. O objetivo do projeto é simular o placar de uma partida de futebol, coletando os dados do jogo, exibindo um resumo e calculando o resultado final.

O projeto foi construído focando na navegação entre telas, passagem de parâmetros e preservação de estado, utilizando exclusivamente os componentes básicos de interface do Compose.

## 🚀 Funcionalidades

O aplicativo é composto por três telas integradas em um fluxo contínuo:

1. **Configuração da Partida (Tela 1):** 
   - Coleta o nome dos times (Time A e Time B) e a quantidade de gols marcados por cada um.
   - Implementa validação de campos (evitando dados em branco ou nulos) e restringe a entrada de gols apenas para números inteiros.
   - Utiliza `rememberSaveable` para preservar os dados digitados mesmo durante a rotação da tela.

2. **Resumo da Partida (Tela 2):** 
   - Exibe os dados informados na tela anterior formatados como um placar (ex: `Time A 2 x 1 Time B`).
   - Permite confirmar os dados para ver o resultado ou voltar para a tela anterior para editar as informações utilizando a funcionalidade de `popBackStack`.

3. **Resultado Final (Tela 3):** 
   - Processa os dados recebidos via rota para determinar o vencedor da partida.
   - Exibe a mensagem correspondente ("[Time A] venceu", "[Time B] venceu" ou "Empate emocionante!").
   - Permite iniciar um "Novo Jogo", retornando à tela inicial e limpando o histórico de navegação para evitar loops no botão de voltar do Android.

## 🛠️ Tecnologias e Arquitetura

* **Linguagem:** Kotlin
* **UI Toolkit:** Jetpack Compose (`Text`, `OutlinedTextField`, `Button`, `Column`)
* **Navegação:** Navigation Compose (`androidx.navigation:navigation-compose`)
* **Gerenciamento de Estado:** `rememberSaveable` e `mutableStateOf`

### Estrutura de Arquivos
* `Screens.kt`: Contém as funções `@Composable` que desenham as três telas e lidam com a validação de UI e regras de negócio de exibição.
* `Navigation.kt`: Gerencia o `NavHost`, define as rotas (usando `sealed class Screen`), extrai os parâmetros passados entre as telas de forma tipada e controla a pilha de navegação.

## ⚙️ Restrições do Projeto (Atendidas)
✅ Apenas 3 telas.
✅ Nenhuma API externa ou banco de dados utilizado.
✅ Nenhuma lista (RecyclerView/LazyColumn) utilizada.
✅ Passagem de parâmetros e conversão de tipos (String/Int) tratadas de forma segura.

## 💻 Como executar o projeto

1. Clone o repositório para a sua máquina.
2. Abra o projeto no **Android Studio** (versão recomendada: Iguana ou superior).
3. Aguarde o Gradle sincronizar (o projeto já inclui a dependência do `navigation-compose` no catálogo de versões).
4. Execute o app em um emulador ou dispositivo físico rodando Android.
