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
import kotlin.reflect.typeOf

/**
 * Define las rutas a pantallas de la app y sus títulos
 */
/*enum class AppScreen(@StringRes val title: Int) {
    ListaPalabras(title = R.string.lista_palabras),
    Palabra(title = R.string.palabra),
    VistaPalabra(title = R.string.vista_palabra)

}*/
@Serializable
object ListaPalabras

@Serializable
data class Palabra(val posPalabra: Int?=null)

@Serializable
data class VistaPalabra(val posPalabra: Int)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListaPalabras) {
        composable<ListaPalabras> {
            ListaPalabrasScreen()
        }

        composable<ListaPalabras> {
            ListaPalabrasScreen(
                onClickNueva = {
                    navController.navigate(Palabra(null))
                               },
                onItemClick = { posPalabra ->
                    navController.navigate(Palabra(posPalabra))
                },
                onItemIconClick = { posPalabra ->
                    navController.navigate(VistaPalabra(posPalabra))
                }
            )
        }
        composable<Palabra> { backStackEntry ->
            val palabra: Palabra = backStackEntry.toRoute()
            PalabraScreen(
                posPalabra =palabra.posPalabra ,
                onVolver = { navController.navigateUp() },
                onMostrar = { navController.navigate(VistaPalabra(palabra.posPalabra!!)) }
            )
        }
        composable<VistaPalabra> { backStackEntry ->
            val palabra: Palabra = backStackEntry.toRoute()
            VistaPalabraScreen(
                posPalabra = palabra.posPalabra!!,
                onVolver = { navController.navigateUp() },
                onVolverAInicio = {  navController.popBackStack(
                    ListaPalabras,
                    inclusive = false) }
            )
        }

    }
}

