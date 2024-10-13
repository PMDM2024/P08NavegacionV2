package net.iessochoa.joseantoniolopez.t08navegacion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PalabraScreen(
    palabra: String,
    onPalabraChange: (String) -> Unit,
    onSave: () -> Unit
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
            onValueChange = onPalabraChange ,
            label = { Text("Palabra") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onSave) {
            Text("Guardar")
        }
    }
}