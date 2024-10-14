package net.iessochoa.joseantoniolopez.t08navegacion.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Pantalla que permitirá añadir una nueva palabra.
 * @param palabra palabra actual
 * @param onPalabraChange Lambda que se ejecuta al cambiar el texto del campo de texto
 * @param onSave Lambda que se ejecuta al pulsar el botón de guardar. Esta acción es responsable le [AppViewModel]
 * @param onMostrar Lambda que se ejecuta al pulsar el botón de mostrar.Esta acción debe navegar a [VistaPalabraScreen]
 */
@Composable
fun PalabraScreen(
    palabra: String,
    onPalabraChange: (String) -> Unit,
    onSave: () -> Unit={},
    onMostrar: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Introduce una palabra")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = palabra,
            onValueChange = onPalabraChange,
            label = { Text("Palabra") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier) {
            Button(onClick = onSave) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onMostrar) {
                Text("Ver Palabra")
            }

        }
    }
}