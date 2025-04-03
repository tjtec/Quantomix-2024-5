package hwr.oop

class Pokémon () {
    var name:String = ""
    var attackType1: String = ""
    var attackType2: String = ""
    var kp: Int = 0
    var attack: Int = 0
    var defense: Int = 0
    var specialAttack: Int = 0
    var specialDefense: Int = 0
    var speed: Int = 0


    fun set_pokémon_variables (pokename:String, typ1:String, typ2:String, input_kp: String, att:String, def:String, sAtt:String, sDef:String) {
        name=pokename
        attackType1=typ1
        attackType2=typ2
        kp=Integer.valueOf(input_kp)
        defense=Integer.valueOf(def)
        attack=Integer.valueOf(att)
        specialAttack=Integer.valueOf(sAtt)
        specialDefense=Integer.valueOf(def)
    }
}

fun addNewPokémon (name: String): Pokémon{
    val newPokémon=Pokémon()

}