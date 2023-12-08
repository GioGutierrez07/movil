package com.example.movil.componentes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.movil.R
import com.example.movil.viewModels.TareasViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import java.util.Date
import java.util.Locale


@Composable
fun MainIconButton(icon: ImageVector, onClick:() -> Unit){
    IconButton(onClick = onClick) {
        Icon(imageVector = icon,
            contentDescription = null,
            tint = Color.Black)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectorFecha(viewModel: TareasViewModel
) {
    val contexto = LocalContext.current
    val calendario = Calendar.getInstance()
    calendario.time = Date()


    // Configurar el DatePickerDialog con la fecha actual
    val selectorFechaDialog = DatePickerDialog(
        contexto,
        { _, año, mes, día,->

            //es la variable que controla la fecha desde el viewmodel
            viewModel.onValue("$día/${mes + 1}/$año","fecha")

            // Crear un objeto DateTimeFormatter para formatear la fecha en el formato deseado
            val fecha = LocalDateTime.of(
                año, mes + 1, día, 0, 0)

            val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
            viewModel.fechaFormato=fecha.format(formatoFecha)

            val zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(viewModel.fechaFormato), ZoneId.systemDefault())
            //convierte esa fech y hora  a milisegundos   y finalmete esos se ututiliza para programar la alarma
            viewModel.alarma = zonedDateTime.toInstant().toEpochMilli()

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

// Composable que muestra un icono y abre un selector de hora al hacer clic en él
@Composable
fun IconHora(viewModel: TareasViewModel) {
    // Obtenemos el contexto actual
    val context = LocalContext.current
    // Creamos una variable de estado para controlar si el diálogo del selector de hora está abierto o no
    var timeDialogOpen by remember { mutableStateOf(false) }

    Row (modifier = Modifier
            .fillMaxWidth()
        .padding(horizontal = 10.dp),
    verticalAlignment = Alignment.CenterVertically){

        // Mostramos un IconButton con un icono de reloj
        IconButton(onClick = {
            // Al hacer clic en el icono, abrimos el diálogo del selector de hora
            timeDialogOpen = true
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_watch_later_24),
                contentDescription = "Seleccionar hora",
                Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
        }

        // Si el diálogo del selector de hora está abierto, lo mostramos
        if (timeDialogOpen) {
            Dialog(onDismissRequest = {
                // Al cerrar el diálogo, actualizamos la variable de estado para reflejar que el diálogo está cerrado
                timeDialogOpen = false
            }) {
                TimePickerDialog(
                    context,
                    { _, hour, minute ->

                        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                        val time = sdf.parse("$hour:$minute")
                        viewModel.horasMilisegundos = time.time

                        // Cuando se selecciona una hora, llamamos a la función onTimeSelected con la hora y los minutos seleccionados
                        // onTimeSelected(hour, minute)
                        viewModel.fechaHora = time.hours.toString()
                        viewModel.fechaMinutos=time.minutes.toString()

                        //viewModel.fechaMinutos = minute.toString()
                        // Y cerramos el diálogo
                        timeDialogOpen = false
                    },
                    12, // Hora inicial
                    0, // Minuto inicial
                    true // Formato de 24 horas
                ).show()
            }
        }
        Text(text = "  "+viewModel.fechaHora +":"+viewModel.fechaMinutos)
    }
}
