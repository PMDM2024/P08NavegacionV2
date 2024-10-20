package net.iessochoa.joseantoniolopez.t08navegacion.data

/**
 * Fuente de datos de la aplicación. Podría ser una base de datos, un servicio web, etc.
 */
object DataSource {
    val listaPalabras = mutableListOf("Hola", "Mundo", "Jetpack", "Compose")

    fun nuevaPalabra(palabra: String) {
        if (!listaPalabras.contains(palabra))
            listaPalabras.add(palabra)
    }
    fun actualizaPalabra(pos:Int,palabra: String) {
        listaPalabras[pos] = palabra
    }
    fun buscarPalabra(cod: Int?) =if(cod==null) "" else listaPalabras[cod]

}