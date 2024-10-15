package net.iessochoa.joseantoniolopez.t08navegacion.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import net.iessochoa.joseantoniolopez.t08navegacion.R

/**
 * Define las rutas a pantallas de la app y sus títulos
 */
enum class AppScreen(@StringRes val title: Int) {
    ListaPalabras(title = R.string.lista_palabras),
    Palabra(title = R.string.palabra),
    VistaPalabra(title = R.string.vista_palabra)

}
//si da error viewModel añadir manualmente import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun App(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
    ){
    // Obtener la pantalla actual de la pila
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Obtener el nombre de la pantalla actual.
    // Si es null, usar ListaPalabras como valor predeterminado que es la pantalla inicial
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.ListaPalabras.name
    )
    Scaffold(
        topBar = {
            //Barra superior de la app
            AppBar(
                //muestra el título de la pantalla
                pantallaActual = currentScreen,
                //si es la primera pantalla no se puede navegar hacia atrás
                //no hay pantalla anterior en la pila de navegación
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                //Esta lambda se ejecuta al pulsar la flecha de navegación hacia atrás
                //de la barra superior
                navegaAtras = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            //pantalla inicial
            startDestination = AppScreen.ListaPalabras.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            //LISTA PALABRAS
            composable(route = AppScreen.ListaPalabras.name) {
                ListaPalabrasScreen(
                    //lista de palabras
                    listaPalabras = uiState.listaPalabras,
                    //Permite navegar hacia la pantalla de palabra desde el botón de nueva palabra
                    onClickNueva = { navController.navigate(AppScreen.Palabra.name) },
                    //Permite navegar hacia la pantalla de palabra desde el click de una palabra
                    onItemClick = {
                        //esta lambda recibe la palabra clicada.
                        // Actualizamos la palabra a mostrar
                        // y navega a PalabrasScreen
                        viewModel.actualizaPalabra(it)
                        //Navegamos a la pantalla de palabra
                        navController.navigate(AppScreen.Palabra.name)
                                  },
                    modifier = Modifier.fillMaxSize()
                )
            }
            //AGREGAR PALABRA
            composable(route = AppScreen.Palabra.name) {
                PalabraScreen(
                    //le pasamos la palabra de la lista o ""
                    uiState.palabra,
                    onPalabraChange = { viewModel.actualizaPalabra(it) },
                    onSave = {
                        //añadimos la palabra a la lista
                        viewModel.actualizaListaPalabras(uiState.palabra)
                        //retrocedemos a la pantalla anterior
                        navController.navigateUp()
                    },
                    onMostrar = {
                        //Navegamos a la pantalla de vista palabra
                        navController.navigate(AppScreen.VistaPalabra.name)
                    }
                )
            }
            //VER VISTA DE PALABRA
            composable(route = AppScreen.VistaPalabra.name){
                VistaPalabraScreen(
                    uiState.palabra,
                    onVolver = {
                        //vamos a la pantalla anterior
                        navController.navigateUp()
                               },
                    onVolverAInicio = {
                        viewModel.actualizaPalabra("")
                        //vamos a la pantalla inicial vaciando la pila de navegación
                        navController.popBackStack(
                            AppScreen.ListaPalabras.name,
                            inclusive = false)
                    }
                )
            }

        }//navhost
    }//scaffold
}



