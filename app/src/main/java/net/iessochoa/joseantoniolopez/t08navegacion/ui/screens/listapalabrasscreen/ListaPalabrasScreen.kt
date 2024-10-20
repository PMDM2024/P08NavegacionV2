package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.components.AppBar
import net.iessochoa.joseantoniolopez.t08navegacion.ui.theme.T08NavegacionTheme

/**
 * Pantalla inicial de la app que muestra la lista de palabras
 * @param listaPalabras lista de palabras a mostrar
 *@param onClickNueva Lambda que se ejecuta al pulsar el botón de nueva palabra.
 * Tedrá que mostra la pantalla de [PalabraScreen]
 * @param onItemClick Lambda que se ejecuta al pulsar una de las palabras. Se la asignaremos al click del Text
 * que muestra la palabra
 *
 */
@Composable
fun ListaPalabrasScreen(
    viewModel: ListaViewModel = viewModel(),
    //listaPalabras: List<String>,
    onClickNueva: () -> Unit = {},
    onItemClick: (pos: Int) -> Unit = {},
    onItemIconClick: (pos: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val uiStateLista by viewModel.uiStateLista.collectAsState()
    Scaffold(
        topBar = {
            //Barra superior de la app
            AppBar(
                //muestra el título de la pantalla
                pantallaActual = "Palabras favoritas",
                //si es la primera pantalla no se puede navegar hacia atrás
                //no hay pantalla anterior en la pila de navegación
                puedeNavegarAtras = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickNueva
            ) {
                Icon(Icons.Filled.Add, "Nueva Palabra")
            }
        }
    ) { padding ->

        Column(modifier = modifier.fillMaxSize()
            .padding(padding)) {
            //lista de palabras
            uiStateLista.listaPalabras.forEachIndexed { pos, item  ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    //este icono nos permitirá mostrar la palabra en la pantalla de vista palabra
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "mostrar",
                        modifier = Modifier.clickable {
                            // ECHO: falta lambda
                            onItemIconClick(pos)

                        }
                    )
                    Text(
                        text = item,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            //navegamos a la pantalla de PalabraScreen
                            .clickable { onItemClick(pos) }
                    )

                }
                //linea separadora
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                //  }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListaPalabrasScreenPreview() {
    T08NavegacionTheme {
        ListaPalabrasScreen(
            //listaPalabras = listOf("Hola", "Mundo", "Jetpack", "Compose"),
            onClickNueva = {})
    }
}