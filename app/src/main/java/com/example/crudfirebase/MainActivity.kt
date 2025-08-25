package com.example.crudfirebase

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crudfirebase.ui.theme.CrudFirebaseTheme
import com.google.firebase.Firebase

class MainActivity : ComponentActivity() {
        val  db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrudFirebaseTheme {
                val navController = rememberNavController()
                Scaffold (modifier = Modifier.fillMaxSize()) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "Login",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("Login") {
                            LoginScreen(
                                onLogin = {userName ->
                                    navController.navigate("Home/${userName}")
                                },
                                onRegisterClick = {
                                    navController.navigate("Register")
                                }
                            )
                        }
                        composable("Register") {
                            RegisterScreen(
                                onRegisterComplete = {
                                    navController.navigate("Login")
                                },
                                onLoginClick = {
                                    navController.navigate("Login")
                                }
                            )
                        }
                        composable(
                            "Home/{userName}",
                            arguments = listOf(navArgument("UserName") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val  userName =  backStackEntry.arguments?.getString("userName") ?: ""
                            HomeScreen(
                                userName = userName,
                                onLogout = {
                                    navController.navigate("Login") {
                                        popUpTo("Home/{userName}") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

    }

}

    @Composable
    fun  RegisterScreen(
      onRegisterComplete: () -> Unit,
      onLoginClick: () -> Unit
    ) {
        var Nome by remember { mutableStateOf("") }
        var Nick by remember { mutableStateOf("") }
        var Email by remember { mutableStateOf("") }
        var Senha by remember { mutableStateOf("") }
        var Tel by remember { mutableStateOf("") }
        var showSenha by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }
        val bd = Firebase.firestore

        val primaryColor = Color(0xFF5EA500)
        val primaryDarkColor = Color(0xFF4A8400)
        val textColor = Color.White
        val cardBackground = Color(0xFF1E1E1E)
        val textColorDark = Color.White
        val labelColor = Color.Gray

        val gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF121212), Color(0xFF121212))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradient),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(containerColor = cardBackground)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()

                        .background(cardBackground)
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        "Registro",
                        fontFamily = FontFamily.Cursive,
                        fontSize = 26.sp,
                        color = primaryColor,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    CustomDarkTextField(
                        value = Nome,
                        onValueChange = { Nome = it },
                        label = "Nome",
                        backgroundColor = cardBackground,
                        textColor = textColorDark,
                        labelColor = labelColor
                    )

                    CustomDarkTextField(
                        value = Nick,
                        onValueChange = { Nick = it },
                        label = "Nickname",
                        backgroundColor = cardBackground,
                        textColor = textColorDark,
                        labelColor = labelColor
                    )

                    CustomDarkTextField(
                        value = Email,
                        onValueChange = { Email = it },
                        label = "E-mail",
                        backgroundColor = cardBackground,
                        textColor = textColorDark,
                        labelColor = labelColor
                    )

                    CustomDarkTextField(
                        value = Senha,
                        onValueChange = { Senha = it },
                        label = "Senha",
                        backgroundColor = cardBackground,
                        textColor = textColorDark,
                        labelColor = labelColor,
                        isPassword = true
                    )

                    CustomDarkTextField(
                        value = Tel,
                        onValueChange = { Tel = it },
                        label = "Telefone",
                        backgroundColor = cardBackground,
                        textColor = textColorDark,
                        labelColor = labelColor
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (Nome.isBlank() || Nick.isBlank() || Email.isBlank() || Senha.isBlank()) {
                                errorMessage = "Preencha todos os campos obrigatórios"
                                return@Button
                            }

                            val User = hashMapOf(
                                "Nome" to Nome,
                                "Nick" to Nick,
                                "Email" to Email,
                                "Senha" to Senha,
                                "Telefone" to Tel
                            )

                            db.collection("banco")
                                .add(usuario)
                                .addOnSucessListener {
                                    Log.d("Firestore", "Documento adicionado com ID: ${it.id}")
                                    onRegisterComplete()
                                }
                                .addOnFailureListener { e ->
                                    errorMessage = "Erro ao cadastrar: ${e.message}"
                                    Log.w("Firestore","Erro ao adicionar documento", e)
                                }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor,
                            contentColor = textColor
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Cadastrar", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            onLoginClick()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = primaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, primaryColor)
                    ) {
                        Text("Já tem uma conta? Faça Login", fontSize = 16.sp)
                    }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(
        userName: String = "Usuário",
        onLogout: () -> Unit
    ) {
        var menuExpanded by remember { mutableStateOf(false) }
        var showRegistros by remember { mutableStateOf(false) }
        val db = Firebase.firestore
        val banco = remember { mutableStateListOf<Map<String, Any>>() }
        val scrollState = rememberScrollState()

        val backgroundColor = Color(0xFF121212)
        val primaryColor = Color(0xFF5EA500)
        val textColor = Color.White
        val  cardBackground = Color(0xFF1E1E1E)

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton( onClick = { menuExpanded = true}){
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = primaryColor
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Listar Registros") },
                        onClick = {
                            menuExpanded = false
                            db.collection("banco")
                                .get()
                                .addOnSuccessListener { result ->
                                    banco.clear()
                                    for (document in result) {
                                        banco.add(document.data)
                                    }
                                    showRegistros = true
                                }
                                .addOnFailureListener { exception ->
                                    Log.w(TAG, "Error Getting Documents.", exception)
                                }
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Sair") },
                        onClick = {
                            menuExpanded = false
                            onLogout()
                        }
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Bem-Vindo(a), $userName!",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 26.sp,
                    color = primaryColor,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                if (showRegistros) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 40.dp)
                    ) {
                        banco.forEachIndexed( index, registro ->
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(cardBackground, shape = RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text("Registro ${index + 1}", color = primaryColor, fontSize = 18.sp)
                            Text("Nome: ${registro["Nome"]}", color = textColor)
                            Text("Nickame: ${registro["Nick"]}", color = textColor)
                            Text("Email: ${registro["Email"]}", color = textColor)
                            Text("Senhha: ${registro["Senha"]}", color = textColor)
                            Text("Telefone: ${registro["Tel"]}", color = textColor)

                            }
                        }
                }
            } else {
                Text(
                    text = "Use o menu no canto superior direito para listar os registros",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                        .padding(horizontal = 24.dp)
                )
            }
        }
    }


    @Composable
    fun LoginScreen(
        onLogin: (String) -> Unit,
        onRegisterClick: () -> Unit
    ) {
        var email by remember { mutableStateOf("") }
        var senha by remember { mutableStateOf("") }
        var showSenha by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }
        val db = Firebase.firestore

        val backgroundColor = Color(0xFF121212)
        val primaryColor = Color(0xFF5EA500)
        val textColor = Color.White
        val cardBackground = Color(0xFF1E1E1E)
        val labelColor = Color.Gray

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                "Login",
                fontFamily = FontFamily.SansSerif,
                fontSize = 26.sp,
                color = primaryColor,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            CustomDarkTextField(
                value = email,
                onValueChange = { email = it },
                label = "E-mail",
                backgroundColor = cardBackground,
                textColor = textColor,
                labelColor = labelColor
            )

            CustomDarkTextField(
                value = senha,
                onValueChange = { email = it },
                label = "Senha",
                backgroundColor = cardBackground,
                textColor = textColor,
                labelColor = labelColor,
                isPassword = !showSenha,
                trailingIcion = {
                    IconButton(onClick = { showSenha = !showSenha }) {
                        Icon(
                            painter = painterResource(
                                id = if (showSenha) R.drawable.visivel else R.drawable.invisivel
                            ),
                            contentDescription = "Toggle Password Visibility",
                            tint = labelColor
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isBlank() || senha.isBlank()) {
                        errorMessage = "Preencha todos os campos"
                        return@Button
                    }

                    db.collection("banco")
                        .whereEqualTo("email", email)
                        .whereEqualTo("senha", senha)
                        .get()
                        .addOnSuccessListener { documents ->
                            if (documents.isEmpty) {
                                errorMessage = "Credenciais Inválidas"
                            } else {
                                val nomeUsuario = documents.documents[0].getString("Nickname") ?: email
                                onLogin(nomeUsuario)
                            }
                        }
                        .addOnFailureListener { exception ->
                            errorMessage = "Erro ao fazer login: ${exception.message}"
                            Log.w("Login", "Erro ao verificar Login", exception)
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Entrar", fontSize = 18.sp, color = Color.White)k
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    onRegisterClick()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = primaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, primaryColor)
            ) {
                Text("Não tem conta? Cadastre-se", fontSize = 16.sp)
            }
        }
    }

    @Composable
    fun CustomDarkTextField(
      value: String,
      onValueChange: (String) -> Unit,
      label: String,
      backgroundColor: Color,
      textColor: Color,
      labelColor: Color,
      isPassword: Boolean = false,
      trailingIcon: @Composable (() -> Unit)? = null
    ) { val primaryDarkColor = Color(0xFF4A8400)

        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, color = labelColor) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                cursorColor = primaryDarkColor,
                focusedLabelColor = labelColor,
                unfocusedLabelColor = labelColor,
                focusedIndicatorColor = primaryDarkColor,
                unfocusedIndicatorColor = Color.Gray
            ),
            trailingIcon = trailingIcon
        )
    }

    @Preview
    @Composable
    fun RegisterPreview(){
        CrudFirebaseTheme {
            RegisterScreen(onRegisterComplete = {}, onLoginClick = {})
        }
    }

    @Preview
    @Composable
    fun HomePreview() {
        CrudFirebaseTheme {

        }
    }