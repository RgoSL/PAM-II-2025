package com.example.loginsqlite.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsqlite.RegDAO
import com.example.loginsqlite.RegEntity
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun Registrar(navController: NavController, regDAO: RegDAO) {
    var nome by remember {
        mutableStateOf("")
    }

    var endereco by remember {
        mutableStateOf("")
    }

    var bairro by remember {
        mutableStateOf("")
    }

    var cep by remember {
        mutableStateOf("")
    }

    var obs by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Fazer um Registro", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = {
                nome = it
            },
            label = {
                Text(text = "Nome")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = endereco,
            onValueChange = {
                endereco = it
            },
            label = {
                Text(text = "Endereço")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bairro,
            onValueChange = {
                bairro = it
            },
            label = {
                Text(text = "Bairro")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = cep,
            onValueChange = {
                cep = it
            },
            label = {
                Text(text = "CEP")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = obs,
            onValueChange = {
                obs = it
            },
            label = {
                Text(text = "Observação")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                val dataReg = LocalDate.now().toString()
                val reg = RegEntity(
                    nome = nome,
                    endereco = endereco,
                    bairro = bairro,
                    cep = cep,
                    obs = obs,
                    data = dataReg
                )
                regDAO.insert(reg)
                navController.navigate("listar")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Salvar")
        }
    }
}