package net.iessochoa.joseantoniolopez.t08navegacion.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppUiState(
    /**
     * Palabra actual en la pantalla de palabra y de vista palabra
     */
    val palabra: String = "",
    /**
     * Lista de palabras actual
     */
    val listaPalabras: MutableList<String> = mutableListOf("Hola", "Mundo", "Jetpack", "Compose")
)

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el estado de la palabra
     */
    fun actualizaPalabra(palabra: String) {
        _uiState.update {
            it.copy(palabra = palabra)
        }
    }

    /**
     *Añade la palabra a la lista de palabras
     */
    fun actualizaListaPalabras(palabra: String) {
        _uiState.update {
            //añadimos la palabra a la lista
            if (!it.listaPalabras.contains(palabra))
                it.listaPalabras.add(palabra)
            it.copy(palabra = "")
        }
    }
}