package com.example.appperfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appperfil.ui.theme.AppPerfilTheme
import com.example.appperfil.ui.theme.RoxoClaro
import com.example.appperfil.ui.theme.RoxoEscuro


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPerfilTheme {
                }
            }
        }
    }

@Composable
fun perfil(){
    Column (

    ) {
      Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        RoxoClaro, RoxoEscuro
                    )
                )
            )
            .size(100.dp),
          contentAlignment = Alignment.Center
      ) {
          Image(
              painter = painterResource(R.drawable.medo),
              contentDescription = "Imagem do Perfil",
              modifier = Modifier
                  .size(90.dp)
                  .clip(CircleShape)

          )

      }

    }
}

@Composable
@Preview

fun PerfilPreview(){
    perfil()
}

