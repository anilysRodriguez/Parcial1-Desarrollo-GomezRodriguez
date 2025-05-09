package com.example.myappcomposeanilysr

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val nombre = intent.getStringExtra("firstKeyName") ?: ""
        val apellido = intent.getStringExtra("secondKeyName") ?: ""
        val nombreCompleto = "$nombre $apellido"

        setContent {
            MaterialTheme  {
                CalculadoraScreen(nombreCompleto)
            }
        }
    }
}

@Composable
fun CalculadoraScreen(nombreUsuario: String) {
    val contexto = LocalContext.current
    val activity = contexto as? Activity

    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculadora", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Text("Usuario: $nombreUsuario", modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))

        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("#1") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("#2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                resultado = operacion(numero1, numero2, "+")
            }) {
                Text("SUMAR")
            }

            Button(onClick = {
                resultado = operacion(numero1, numero2, "-")
            }) {
                Text("RESTAR")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                resultado = operacion(numero1, numero2, "*")
            }) {
                Text("MULTIPLICAR")
            }

            Button(onClick = {
                resultado = operacion(numero1, numero2, "/")
            }) {
                Text("DIVIDIR")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Resultado", fontSize = 18.sp)
        OutlinedTextField(
            value = resultado,
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            activity?.finish()
        }) {
            Text("Salir")
        }
    }
}

fun operacion(n1: String, n2: String, operador: String): String {
    val num1 = n1.toDoubleOrNull()
    val num2 = n2.toDoubleOrNull()

    return if (num1 != null && num2 != null) {
        when (operador) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> if (num2 != 0.0) (num1 / num2).toString() else "División por 0"
            else -> "Operación inválida"
        }
    } else {
        "Números inválidos"
    }
}


private fun MainActivity2.MyApplicationComposeTheme(function: @Composable Any) {}
