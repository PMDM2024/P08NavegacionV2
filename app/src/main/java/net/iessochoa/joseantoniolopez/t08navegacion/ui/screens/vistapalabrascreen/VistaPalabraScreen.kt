package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.vistapalabrascreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t08navegacion.R
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.components.AppBar

/**
 * Muestra la palabra actual en un formato vistoso
 * @param palabra palabra actual
 * @param onVolver Lambda que se ejecuta al pulsar el botón de volver.
 */
@Composable
fun VistaPalabraScreen(
    viewModel: VistaPalabraViewModel = viewModel(),
    posPalabra: Int,
    onVolver: () -> Unit,
    onVolverAInicio: () -> Unit
) {

    viewModel.buscarPalabra(posPalabra)
    val uiVistaStatePalabra by viewModel.uiStateVistaPalabra.collectAsState()
    Scaffold(
        topBar = {
            //Barra superior de la app
            AppBar(
                //muestra el título de la pantalla
                tituloPantallaActual = "Ver Palabra: " + uiVistaStatePalabra.palabra,

                //si es la primera pantalla no se puede navegar hacia atrás
                //no hay pantalla anterior en la pila de navegación
                puedeNavegarAtras = true,
                //Esta lambda se ejecuta al pulsar la flecha de navegación hacia atrás
                //de la barra superior
                navegaAtras = onVolver
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //creamos una fuente, desde una fuente descargada
            val MiFuente = FontFamily(
                Font(R.font.dancing_script, FontWeight.Normal) // Asocia la fuente con su peso
            )
            Text(
                text = uiVistaStatePalabra.palabra,
                //creamos el estilo del texto con una fuente descargada
                style = TextStyle(
                    fontFamily = MiFuente,
                    fontSize = 80.sp,
                    textAlign = TextAlign.Center
                ),
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
            )

            //Volverá a la pantalla anterior
            Row(modifier = Modifier,
                ) {
                Button(onClick = onVolver) {
                    Text("Volver Atrás")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onVolverAInicio) {
                    Text("Volver a Inicio")
                }


            }



        }
    }
}
