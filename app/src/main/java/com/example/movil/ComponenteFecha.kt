import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movil.BotonPersonalizado
import com.example.movil.IconoPersonalizable
import com.example.movil.R
import java.util.Date

@Composable
fun SelectorFecha(
    onFechaSeleccionada: (String) -> Unit
) {
    val contexto = LocalContext.current
    val calendario = Calendar.getInstance()
    calendario.time = Date()
    val fechaSeleccionada = remember { mutableStateOf("") }

    // Configurar el DatePickerDialog con la fecha actual
    val selectorFechaDialog = DatePickerDialog(
        contexto,
        { _, año, mes, día ->
            fechaSeleccionada.value = "$día/${mes + 1}/$año"
            onFechaSeleccionada(fechaSeleccionada.value)
        },
        calendario.get(Calendar.YEAR),
        calendario.get(Calendar.MONTH),
        calendario.get(Calendar.DAY_OF_MONTH)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {




        IconoPersonalizable(onClick = {  selectorFechaDialog.show() }, icono = R.drawable.fecha)
        // Botón para abrir el selector de fechas
        //BotonPersonalizado(texto = "Elige una fecha", onClick = {  selectorFechaDialog.show() })

        Spacer(modifier = Modifier.size(10.dp))

        // Mostrar la fecha seleccionada
        Text(
            text = "Fecha: ${fechaSeleccionada.value}",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun Fecha() {
    var fechaSeleccionada by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        // verticalArrangement = Arrangement.Center,
        // horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectorFecha { fecha ->
            fechaSeleccionada = fecha //guardamos la fecha seleccionada
        }
        Spacer(modifier = Modifier.size(10.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun VistaPrevia() {
    Fecha()
}