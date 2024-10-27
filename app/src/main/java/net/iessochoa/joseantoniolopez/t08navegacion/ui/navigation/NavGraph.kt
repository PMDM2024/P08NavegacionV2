package net.iessochoa.joseantoniolopez.t08navegacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen.ListaPalabrasScreen
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen.PalabraScreen
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.vistapalabrascreen.VistaPalabraScreen



//Grafo de Navegación
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        //pantalla de inicio
        startDestination = ListaPalabrasDestination
    ) {
        //ruta a la pantalla ListaPalabrasScreen. Pantalla inicial de la app
        composable<ListaPalabrasDestination> {
            ListaPalabrasScreen(
                onClickNueva = {
                    // Navegamos a la pantalla PalabraScreen. Pasamos null porque es una nueva palabra
                    navController.navigate(PalabraDestination())
                },
                //Navegamos a la pantalla Palabra editanto una palabra existente.
                // Pasamos la posición de la palabra en la lista
                onItemModificarClick = { posPalabra ->
                    navController.navigate(PalabraDestination(posPalabra))
                },
                //Navegamos a la pantalla VistaPalabraScreen.
                // Pasamos la posición de la palabra en la lista
                onItemVerClick = { posPalabra ->
                    navController.navigate(VistaPalabraDestination(posPalabra))
                }
            )
        }
        //ruta a la pantalla PalabraScreen.
        //backStarckEntry contiene los paráetros pasados en la navegación
        composable<PalabraDestination> { backStackEntry ->
            //recuperamos el parámetro posPalabra de la navegación
            val palabra: PalabraDestination = backStackEntry.toRoute()
            PalabraScreen(
                posPalabra = palabra.posPalabra,
                //pasamos la lambda para volver a la pantalla anterior
                onVolver = {
                    navController.navigateUp()
                },
                //pasamos la lambda para navegar a la pantalla de vista palabra si no es nueva
                onMostrar = {
                    if (palabra.posPalabra != null)
                        navController.navigate(VistaPalabraDestination(palabra.posPalabra))
                }
            )
        }
        //ruta a la pantalla VistaPalabraScreen.
        composable<VistaPalabraDestination> { backStackEntry ->
            //recuperamos el parámetro posPalabra de la navegación
            val palabra: VistaPalabraDestination = backStackEntry.toRoute()
            VistaPalabraScreen(
                posPalabra = palabra.posPalabra,
                onVolver = {
                    navController.navigateUp()
                },
                onVolverAInicio = {
                    //vaciamos la pila de navegación y mostramos la pantalla de inicio
                    navController.popBackStack(
                        ListaPalabrasDestination,
                        inclusive = false
                    )
                }
            )
        }

    }
}

