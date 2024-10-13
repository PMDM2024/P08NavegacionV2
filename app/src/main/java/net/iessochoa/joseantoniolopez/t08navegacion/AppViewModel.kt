package net.iessochoa.joseantoniolopez.t08navegacion

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppUiState(
    val palabra: String = "",
    val palabras: MutableList<String> = mutableListOf("Hola", "Mundo", "Jetpack", "Compose")
)

class AppViewModel :ViewModel(){
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun updateUiStatePalabra(palabra: String) {
        _uiState.update {
            it.copy(palabra = palabra)
        }
    }
    fun updateUiStatePalabras(palabra: String) {
        _uiState.update {
            it.palabras.add(palabra)
            it.copy(palabra = "")

        }


    }

}