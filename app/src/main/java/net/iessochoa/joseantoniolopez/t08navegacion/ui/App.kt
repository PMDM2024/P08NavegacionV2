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
 * Define las pantallas de la app y sus títulos
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
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.ListaPalabras.name
    )
    Scaffold(
        topBar = {
            AppBar(
                pantallaActual = currentScreen,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                navegaAtras = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = AppScreen.ListaPalabras.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            //LISTA PALABRAS
            composable(route = AppScreen.ListaPalabras.name) {
                ListaPalabrasScreen(
                    listaPalabras = uiState.palabras,
                    onClickNueva = { navController.navigate(AppScreen.Palabra.name) },
                    onItemClick = {
                        viewModel.updateUiStatePalabra(it)
                        navController.navigate(AppScreen.Palabra.name)
                                  },
                    modifier = Modifier.fillMaxSize()
                )
            }
            //AGREGAR PALABRA
            composable(route = AppScreen.Palabra.name) {
                PalabraScreen(
                    uiState.palabra,
                    onPalabraChange = { viewModel.updateUiStatePalabra(it) },
                    onSave = {
                        viewModel.updateUiStatePalabras(uiState.palabra)
                        //navController.navigate(AppScreen.ListaPalabras.name,)
                        //retrocedemos a la pantalla anterior
                        navController.navigateUp()
                    },
                    onMostrar = {
                        navController.navigate(AppScreen.VistaPalabra.name)
                    }
                )
            }
            //VER PALABRA
            composable(route = AppScreen.VistaPalabra.name){
                VistaPalabraScreen(
                    uiState.palabra,
                    onVolver = {
                        navController.navigateUp()
                               },
                    onVolverAInicio = {
                        viewModel.updateUiStatePalabra("")
                        navController.popBackStack(
                            AppScreen.ListaPalabras.name,
                            inclusive = false)
                    }
                )
            }

        }//navhost
    }//scaffold
}
//fun navegarAPalabraScreen


