package com.example.myappcomposeanilysr

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValidarNotaApp()
        }
    }
}

@Composable
fun ValidarNotaApp() {
    var notaInput by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFD8EAFE), Color(0xFFB0D6F7))
                )
            ),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text("Parcial #1", fontSize = 24.sp, color = Color(0xFF1B365C))
            Spacer(modifier = Modifier.height(12.dp))
            Text("Anilys Rodríguez", fontSize = 18.sp)
            Text("Martin Gómez", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(28.dp))

            OutlinedTextField(
                value = notaInput,
                onValueChange = { notaInput = it },
                label = { Text("Ingrese la nota a validar") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                onClick = {
                    val resultado = validarNota(notaInput)
                    Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
                }
            ) {
                Text("Validar")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Guía de puntajes
            Text("Validar la nota según el puntaje:", fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))
            Text("De 91 a 100 = A (Excelente)", fontSize = 15.sp)
            Text("De 81 a 90 = B (Bueno)", fontSize = 15.sp)
            Text("De 71 a 80 = C (Regular)", fontSize = 15.sp)
            Text("De 61 a 70 = D (Mas o menos regular)", fontSize = 15.sp)
            Text("Menos de 61 = No Aprobado", fontSize = 15.sp)
        }
    }
}

fun validarNota(nota: String): String {
    val valor = nota.toIntOrNull()
    return when {
        valor == null -> "Ingrese un número válido"
        valor in 91..100 -> "A (Excelente)"
        valor in 81..90 -> "B (Bueno)"
        valor in 71..80 -> "C (Regular)"
        valor in 61..70 -> "D (Más o menos regular)"
        valor in 0..60 -> "No Aprobado"
        else -> "Nota fuera de rango"
    }
}
