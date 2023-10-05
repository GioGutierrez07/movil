package com.example.movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movil.ui.theme.MovilTheme
import com.example.myapplication.compoentesPureba

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    muestraComponntes()
                }
            }
        }
    }
}

@Composable
fun muestraComponntes(modifier: Modifier = Modifier) {
    compoentesPureba(onAddClick = { /*TODO*/ },
        {tareaCard(
            nombre = "hacer tarea",
            descripcion = "Debo hacer la tarea de movil",
            imagen = R.drawable.astronauta  )})
}

