package mx.ipn.escom.l2_client

class GustoQuetzalliano(
    tipoDeGusto:Int,
    nombreDelGusto:String,
    nivelDeFelicidad:Float,
    nivelDeMusicalidad:Double,
    esKaskade:Boolean
) {
    val tipoDeGusto = tipoDeGusto
    val nombreDelGusto = nombreDelGusto
    val nivelDeFelicidad = nivelDeFelicidad
    val nivelDeMusicalidad = nivelDeMusicalidad
    val esKaskade = esKaskade
    val mensaje:String = if(esKaskade) "Quetzalli en verdad ama este gusto" else "Podrá ser gusto, pero jamás mejor que Kaskade"

    //En Kotlin, los getters y setters son autogenerados con cada variable, asi que no son necesarios

}