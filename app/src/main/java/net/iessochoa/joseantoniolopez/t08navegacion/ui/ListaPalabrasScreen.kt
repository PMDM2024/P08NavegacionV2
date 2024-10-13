package net.iessochoa.joseantoniolopez.t08navegacion.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.joseantoniolopez.t08navegacion.ui.theme.T08NavegacionTheme

@Composable
fun ListaPalabrasScreen(
    listaPalabras: List<String>,
    onClickNueva: () -> Unit = {},
    onItemClick: (palabra: String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        listaPalabras.forEachIndexed { index, item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
            )
            // if (index < listaPalabras.size - 1) {
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            //  }
        }
        Spacer(Modifier.padding(16.dp))
        OutlinedButton(
            modifier = Modifier
                //.align(LineHeightStyle.Alignment.CenterHorizontally)
                .padding(8.dp),
            onClick = onClickNueva
        ) {
            Text(text = "Nueva Palabra")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ListaPalabrasScreenPreview() {
    T08NavegacionTheme {
        ListaPalabrasScreen(
            listaPalabras = listOf("Hola", "Mundo", "Jetpack", "Compose"),
            onClickNueva = {})
    }
}