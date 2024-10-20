package net.iessochoa.joseantoniolopez.t08navegacion.data

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