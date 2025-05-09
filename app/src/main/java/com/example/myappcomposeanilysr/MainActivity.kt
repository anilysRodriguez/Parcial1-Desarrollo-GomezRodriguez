package com.example.myappcomposeanilysr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myappcomposeanilysr.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.calculator),
            contentDescription = "Logo UTP",
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Bienvenida UTP Calculadora!!", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            Toast.makeText(context, "Saluditos desde Compose practica", Toast.LENGTH_SHORT).show()
        }) {
            Text("Da clic aquí para un mensaje")
        }

        Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.height(16.dp))
        CamposFormulario()
    }
}


@Composable
fun CamposFormulario() {
    val context = LocalContext.current
    var usuario by remember { mutableStateOf("") }
    var claveIngresada by rememberSaveable { mutableStateOf("") }

    val longitudValida = claveIngresada.length >= 8
    val tieneMayuscula = claveIngresada.any { it.isUpperCase() }
    val tieneMinuscula = claveIngresada.any { it.isLowerCase() }
    val tieneNumero = claveIngresada.any { it.isDigit() }
    val tieneGuionBajo = claveIngresada.contains("_")

    val claveValida = longitudValida && tieneMayuscula && tieneMinuscula && tieneNumero && tieneGuionBajo

    TextField(
        value = usuario,
        onValueChange = { usuario = it },
        label = { Text("Nombre de usuario") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold)
    )

    Spacer(modifier = Modifier.height(10.dp))

    TextField(
        value = claveIngresada,
        onValueChange = { claveIngresada = it },
        label = { Text("Clave") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Requisitos de la clave:", fontWeight = FontWeight.Bold)
        ValidacionTexto(" Mínimo 8 caracteres", longitudValida)
        ValidacionTexto(" Una letra mayúscula", tieneMayuscula)
        ValidacionTexto(" Una letra minúscula", tieneMinuscula)
        ValidacionTexto(" Un número", tieneNumero)
        ValidacionTexto(" Un guion bajo (_)", tieneGuionBajo)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
            if (claveValida) {
                val intent = Intent(context, MainActivity2::class.java)
                intent.putExtra("firstKeyName", usuario)
                intent.putExtra("secondKeyName", "")
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "La clave no cumple todos los requisitos", Toast.LENGTH_LONG).show()
            }
        },
        enabled = claveValida
    ) {
        Text("Enviar")
    }
}

@Composable
fun ValidacionTexto(texto: String, valido: Boolean) {
    Text(
        text = if (valido) "✓ $texto" else "✗ $texto",
        color = if (valido) Color(0xFF2E7D32) else Color.Red,
        fontSize = 14.sp
    )
}


