package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.joseantoniolopez.t08navegacion.data.DataSource
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen.UiStatePalabra
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.vistapalabrascreen.UiStateVistaPalabra

data class AppUiState(
    /**
     * Palabra actual en la pantalla de palabra y de vista palabra
     */
    val palabra: String = "",
    /**
     * Lista de palabras actual
     */
    val listaPalabras: MutableList<String> = DataSource.listaPalabras
)

class ListaViewModel : ViewModel() {
    private val _uiStateLista = MutableStateFlow(ListaUiState())
    val uiStateLista: StateFlow<ListaUiState> = _uiStateLista.asStateFlow()



    fun listaPalabras() = _uiStateLista.value.listaPalabras
    /**
     *Añade la palabra a la lista de palabras
     */


    }
