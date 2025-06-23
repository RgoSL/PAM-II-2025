package com.example.loginsqlite.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsqlite.RegDAO
import com.example.loginsqlite.RegEntity
import kotlinx.coroutines.launch

@Composable

fun Listar(navController: NavController, regDAO: RegDAO) {
  var registros by remember {
      mutableStateOf(listOf<RegEntity>())
  }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            registros = regDAO.getAll()
        }
    }

    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Lista de Registros", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(registros) { reg ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Column (
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Nome : ${reg.nome}")
                        Text(text = "Endereço : ${reg.endereco}")
                        Text(text = "Bairro : ${reg.bairro}")
                        Text(text = "CEP : ${reg.cep}")
                        Text(text = "Observações : ${reg.obs}")
                        Text(text = "Data do Cadastro : ${reg.data}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("cadastro")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Novo Registro")
        }

    }




}