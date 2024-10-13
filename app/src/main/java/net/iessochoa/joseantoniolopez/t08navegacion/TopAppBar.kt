package net.iessochoa.joseantoniolopez.t08navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * Composable que define la barra de navegación superior de la app
 * @param pantallaActual pantalla actual. Permite mostrar el titulo correspondiente.
 * @param puedeNavegarAtras indica si se puede navegar hacia atrás. La pantalla de inicio no puede tener navegación hacia atrás
 * @param navegaAtras acción de navegación hacia atrás. Lambda que se ejecuta al pulsar el botón de navegación hacia atrás
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    pantallaActual: AppScreen,
    puedeNavegarAtras: Boolean,
    navegaAtras: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(pantallaActual.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            //si es la primera pantalla no se muestra el botón de navegación
            if (puedeNavegarAtras) {
                IconButton(onClick = navegaAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        //recuerda que el texto tiene que ir en string.xml
                        contentDescription = "Ir atrás"
                    )
                }
            }
        }
    )

}