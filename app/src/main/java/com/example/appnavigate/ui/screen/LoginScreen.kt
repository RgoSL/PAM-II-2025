package com.example.appnavigate.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appnavigate.ui.theme.AppNavigateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var text by rememberSaveable { mutableStateOf("") }

    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
        Text(text = "Você Está na Tela de Login", modifier = Modifier.padding(16.dp))

        OutlinedTextField(
            value = text,
            onValueChange = {text = it },
            modifier = Modifier.padding(16.dp),
            label = { Text(text = "Digite Seu Nome de Usuário") }
        )

        Button(onClick = {
            val name = text.ifEmpty { "Etec" }
            navController.navigate("Home/$name")
        }, modifier = Modifier.padding(16.dp)) {
            Text(text = "Navegar Para a Home")
        }
    }
}

@Composable
@Preview

fun AbratePorcaria (){
    AppNavigateTheme {
        LoginScreen(navController = rememberNavController())
    }
}