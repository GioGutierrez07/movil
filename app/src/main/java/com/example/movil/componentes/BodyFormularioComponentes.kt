package com.example.movil.componentes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movil.R
import com.example.movil.viewModels.TareasViewModel

import java.util.Date


@Composable
fun MainIconButton(icon: ImageVector, onClick:() -> Unit){
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun MainButton(name:String, backColor: Color, color: Color, onClick:() -> Unit){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        contentColor = color,
        containerColor = backColor
    ) ) {
        Text(text = name)
    }
}


@Composable
fun Alert(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = { onConfirmClick()}) {
                Text(text = confirmText)
            }
        }
    )
}

@Composable
fun SelectorFecha(viewModel: TareasViewModel
) {
    val contexto = LocalContext.current
    val calendario = Calendar.getInstance()
    calendario.time = Date()


    // Configurar el DatePickerDialog con la fecha actual
    val selectorFechaDialog = DatePickerDialog(
        contexto,
        { _, año, mes, día ->
            viewModel.onValue("$día/${mes + 1}/$año","fecha")

        },
        calendario.get(Calendar.YEAR),
        calendario.get(Calendar.MONTH),
        calendario.get(Calendar.DAY_OF_MONTH)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconoPersonalizable(
            onClick = { selectorFechaDialog.show() },
            icono = R.drawable.fecha)
        // Botón para abrir el selector de fechas
        //BotonPersonalizado(texto = "Elige una fecha", onClick = {  selectorFechaDialog.show() })

        Spacer(modifier = Modifier.size(10.dp))

        // Mostrar la fecha seleccionada
        Text(
            text = "Fecha: ${viewModel.estado.fecha}",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
    }

}