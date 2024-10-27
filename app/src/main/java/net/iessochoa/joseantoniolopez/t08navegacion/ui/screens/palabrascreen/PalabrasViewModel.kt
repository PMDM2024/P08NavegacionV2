package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.joseantoniolopez.t08navegacion.data.DataSource

/**
 * Estado y acciones de la pantalla de PalabraScreen.
 */
class PalabrasViewModel : ViewModel() {
    private val _uiStatePalabra = MutableStateFlow(UiStatePalabra())
    val uiStatePalabra: StateFlow<UiStatePalabra> = _uiStatePalabra.asStateFlow()

    /**
     * Actualiza la palabra en la posición indicada. No actualiza
     * el estado porque sale de la pantalla
     */
    fun guardarPalabra(pos: Int, palabra: String) {
         DataSource.actualizaPalabra(pos, palabra)
    }
    /**
     * Añade una nueva palabra a la lista.
     */
    fun nuevaPalabra(palabra: String) {
        DataSource.nuevaPalabra(palabra)
    }

    /**
     * Busca la palabra en la posición indicada.
     */
    fun buscarPalabra(cod: Int?) {
        _uiStatePalabra.update {
            it.copy(
                palabra = if (cod == null) "" else DataSource.buscarPalabra(cod)
            )
        }
    }

    /**
     * Actualiza la palabra  de FieldText
     */
    fun actualizaEstadoPalabra(palabra: String) {
        _uiStatePalabra.update {
            it.copy(
                palabra = palabra
            )
        }
    }

}