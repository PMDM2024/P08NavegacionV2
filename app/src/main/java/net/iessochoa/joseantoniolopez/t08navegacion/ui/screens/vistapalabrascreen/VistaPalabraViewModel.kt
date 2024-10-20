package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.vistapalabrascreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.joseantoniolopez.t08navegacion.data.DataSource
import net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.palabrascreen.UiStatePalabra

class VistaPalabraViewModel: ViewModel() {
    private val _uiStateVistaPalabra = MutableStateFlow(UiStateVistaPalabra())
    val uiStateVistaPalabra: StateFlow<UiStateVistaPalabra> = _uiStateVistaPalabra.asStateFlow()


    fun buscarPalabra(cod: Int?) {
        _uiStateVistaPalabra.update {
            it.copy(
                palabra = if(cod==null) "" else DataSource.buscarPalabra(cod))
        }
    }

}