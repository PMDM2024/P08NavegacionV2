package net.iessochoa.joseantoniolopez.t08navegacion.ui.screens.listapalabrasscreen

import net.iessochoa.joseantoniolopez.t08navegacion.data.DataSource
//Definimos el estado del componente
data class ListaUiState(
    /**
     * Lista de palabras actual
     */
    val listaPalabras: MutableList<String> = DataSource.listaPalabras
)