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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = palabra,
            style =MaterialTheme.typography.displayLarge,
            color = Color.Green,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = onVolver) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onVolverAInicio) {
            Text("volver a inicio")
        }
    }
}
