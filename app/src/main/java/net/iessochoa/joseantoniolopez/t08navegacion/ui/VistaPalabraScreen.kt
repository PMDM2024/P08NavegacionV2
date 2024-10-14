package net.iessochoa.joseantoniolopez.t08navegacion.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.joseantoniolopez.t08navegacion.R

/**
 * Muestra la palabra actual en un formato vistoso
 * @param palabra palabra actual
 * @param onVolver Lambda que se ejecuta al pulsar el botón de volver.
 */
@Composable
fun VistaPalabraScreen(
    palabra: String,
    onVolver: () -> Unit,
    onVolverAInicio: () -> Unit
)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //creamos una fuente, desde una fuente descargada
        val MiFuente = FontFamily(
            Font(R.font.dancing_script, FontWeight.Normal) // Asocia la fuente con su peso
        )
        Text(
            text = palabra,
            //creamos el estilo del texto con una fuente descargada
            style =TextStyle(
                fontFamily = MiFuente,
                fontSize = 80.sp,
                textAlign = TextAlign.Center
            ),
            color = Color.Blue,
            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
        )
        //Volverá a la pantalla anterior
        Button(onClick = onVolver) {
            Text("Volver Atrás")
        }

        Spacer(modifier = Modifier.height(8.dp))
        //Volverá a la pantalla inicial
        Button(onClick = onVolverAInicio) {
            Text("Volver a inicio")
        }
    }
}
