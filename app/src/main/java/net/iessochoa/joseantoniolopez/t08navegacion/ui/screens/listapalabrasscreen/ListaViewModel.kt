package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * ViewModel que contiene el estado de la pantalla ListaPalabrasScreen
 * Mantenemos la lista de palabras en una lista mutable recuperada de DataSource
 */
class ListaViewModel : ViewModel() {
    private val _uiStateLista = MutableStateFlow(ListaUiState())
    val uiStateLista: StateFlow<ListaUiState> = _uiStateLista.asStateFlow()

    }
