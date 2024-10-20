package net.iessochoa.joseantoniolopez.t08navegacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen.ListaPalabrasScreen
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen.PalabraScreen
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.vistapalabrascreen.VistaPalabraScreen

//Pantallas:
// Definimos las pantallas que queremos navegar donde  las llamamos mediante los siguientes
// elementos Serializable

// ListaPalabrasScreen: Pantalla inicial de la app que muestra la lista de palabras
//la definimos como object porque no tiene parámetros
@Serializable
object ListaPalabras

//las Pantallas con parámetros se definen como data class
@Serializable
data class Palabra(val posPalabra: Int? = null)

@Serializable
data class VistaPalabra(val posPalabra: Int)

//Grafo de Navegación
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        //pantalla de inicio
        startDestination = ListaPalabras
    ) {
        //ruta a la pantalla ListaPalabrasScreen. Pantalla inicial de la app
        composable<ListaPalabras> {
            ListaPalabrasScreen(
                onClickNueva = {
                    // Navegamos a la pantalla PalabraScreen. Pasamos null porque es una nueva palabra
                    navController.navigate(Palabra())
                },
                //Navegamos a la pantalla Palabra editanto una palabra existente.
                // Pasamos la posición de la palabra en la lista
                onItemModificarClick = { posPalabra ->
                    navController.navigate(Palabra(posPalabra))
                },
                //Navegamos a la pantalla VistaPalabraScreen.
                // Pasamos la posición de la palabra en la lista
                onItemVerClick = { posPalabra ->
                    navController.navigate(VistaPalabra(posPalabra))
                }
            )
        }
        //ruta a la pantalla PalabraScreen.
        //backStarckEntry contiene los paráetros pasados en la navegación
        composable<Palabra> { backStackEntry ->
            //recuperamos el parámetro posPalabra de la navegación
            val palabra: Palabra = backStackEntry.toRoute()
            PalabraScreen(
                posPalabra = palabra.posPalabra,
                //pasamos la lambda para volver a la pantalla anterior
                onVolver = {
                    navController.navigateUp()
                },
                //pasamos la lambda para navegar a la pantalla de vista palabra si no es nueva
                onMostrar = {
                    if (palabra.posPalabra != null)
                        navController.navigate(VistaPalabra(palabra.posPalabra))
                }
            )
        }
        //ruta a la pantalla VistaPalabraScreen.
        composable<VistaPalabra> { backStackEntry ->
            //recuperamos el parámetro posPalabra de la navegación
            val palabra: VistaPalabra = backStackEntry.toRoute()
            VistaPalabraScreen(
                posPalabra = palabra.posPalabra,
                onVolver = {
                    navController.navigateUp()
                },
                onVolverAInicio = {
                    //vaciamos la pila de navegación y mostramos la pantalla de inicio
                    navController.popBackStack(
                        ListaPalabras,
                        inclusive = false
                    )
                }
            )
        }

    }
}

