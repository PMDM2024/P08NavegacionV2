package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import net.iessochoa.joseantoniolopez.t08navegacion.R
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.components.AppBar
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen.ListaViewModel

/**
 * Pantalla que permitirá añadir una nueva palabra o modificarla. Si la posición de la palabra en null
 * la palabra será nueva
 * @param palabra palabra actual
 * @param onPalabraChange Lambda que se ejecuta al cambiar el texto del campo de texto
 * @param onSave Lambda que se ejecuta al pulsar el botón de guardar. Esta acción es responsable le [ListaViewModel]
 * @param onMostrar Lambda que se ejecuta al pulsar el botón de mostrar.Esta acción debe navegar a [VistaPalabraScreen]
 */
@Composable
fun PalabraScreen(
    viewModel: PalabrasViewModel = viewModel(),
    posPalabra: Int?,
    onMostrar: () -> Unit = {},
    onVolver: () -> Unit = {},
) {
    viewModel.buscarPalabra(posPalabra)
    val isNuevaPalabra = posPalabra == null
    val uiStatePalabra by viewModel.uiStatePalabra.collectAsState()
    //val uiStatePalabra by viewModel.uiStatePalabra.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            //Barra superior de la app
            AppBar(
                //muestra el título de la pantalla
                tituloPantallaActual = if (isNuevaPalabra) "Nueva Palabra"
                                        else "Actualizar: " + uiStatePalabra.palabra,
                //si es la primera pantalla no se puede navegar hacia atrás
                //no hay pantalla anterior en la pila de navegación
                puedeNavegarAtras = true,
                //Esta lambda se ejecuta al pulsar la flecha de navegación hacia atrás
                //de la barra superior
                navegaAtras = onVolver
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick ={
                    if (uiStatePalabra.palabra.isNotEmpty()) {
                        if (isNuevaPalabra)
                            viewModel.nuevaPalabra(uiStatePalabra.palabra)
                        else
                            viewModel.guardarPalabra(posPalabra!!, uiStatePalabra.palabra)
                        onVolver()
                    }else{
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "La palabra no puede estar vacía",
                                duration = SnackbarDuration.Short
                            )
                        }


                    }
                }
            ) {
                Icon(
                    painterResource(R.drawable.ic_guardar)
                    , "Nueva Palabra")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Introduce una palabra")
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiStatePalabra.palabra,
                onValueChange = {viewModel.actualizaPalabra(it) },
                label = { Text("Palabra") },
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier) {

                if(!isNuevaPalabra){
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(//Mostrar
                        onClick = onMostrar,
                        enabled = uiStatePalabra.palabra.isNotEmpty()
                    ) {
                        Text("Ver Palabra")
                    }
                }


            }
        }

    }

}