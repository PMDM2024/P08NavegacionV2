package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.joseantoniolopez.t08navegacion.data.DataSource

class PalabrasViewModel: ViewModel() {
    private val _uiStatePalabra = MutableStateFlow(UiStatePalabra())
    val uiStatePalabra: StateFlow<UiStatePalabra> = _uiStatePalabra.asStateFlow()

    fun guardarPalabra(pos:Int, palabra: String) {
        _uiStatePalabra.update {
            DataSource.actualizaPalabra(pos,palabra)
            it
        }
    }
     fun nuevaPalabra(palabra: String) {
         DataSource.nuevaPalabra(palabra)
     }
    fun buscarPalabra(cod: Int?) {
        _uiStatePalabra.update {
            it.copy(
                palabra = if(cod==null) "" else DataSource.buscarPalabra(cod))
        }
    }
    fun actualizaPalabra(palabra: String) {
            _uiStatePalabra.update {
                it.copy(
                    palabra = palabra
                )
            }
    }

}