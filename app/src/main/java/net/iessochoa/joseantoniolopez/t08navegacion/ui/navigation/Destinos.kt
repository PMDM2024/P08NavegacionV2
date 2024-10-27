package net.iessochoa.joseantoniolopez.t08navegacion.ui.navigation

import kotlinx.serialization.Serializable

//Pantallas:
// Definimos las pantallas que queremos navegar donde  las llamamos mediante los siguientes
// elementos Serializable

// ListaPalabrasScreen: Pantalla inicial de la app que muestra la lista de palabras
//la definimos como object porque no tiene parámetros
@Serializable
object ListaPalabras

//las Pantallas con parámetros se definen como data class
@Serializable
data class Palabra(val posPalabra: Int? = null)

@Serializable
data class VistaPalabra(val posPalabra: Int)