package com.example.appperfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.appperfil.ui.theme.AppPerfilTheme
import com.example.appperfil.ui.theme.RoxoClaro
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPerfilTheme {
                Forms()
            }
        }
    }
}

@Composable
fun Forms() {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(RoxoClaro, Color.Cyan)
    )

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(gradientBrush),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Imagem Circular",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        InputField(label = "Nome")
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Email")
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Telefone")
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Endereço")

        Spacer(modifier = Modifier.height(36.dp))

        CustomButton(label = "Salvar")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(label: String) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Blue,
            unfocusedLabelColor = Color.Gray,

            cursorColor = Color.Blue,

        )
    )
}

@Composable
fun CustomButton(label: String) {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp),
        content = {
            Text(
                text = label,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppPerfilTheme {
        Forms()
    }
}
