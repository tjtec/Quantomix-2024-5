package hwr.oop

import java.io.File

class Pokémon () {
//    var name:String = ""
//    var attackType1: String = ""
//    var attackType2: String = ""
//    var kp: Int = 0
//    var attack: Int = 0
//    var defense: Int = 0
//    var specialAttack: Int = 0
//    var specialDefense: Int = 0
//    var speed: Int = 0
    fun set_pokémon_variables (pokename:String, typ1:String, typ2:String, inputKp: String, att:String, def:String, sAtt:String, sDef:String, inSpeed:String) {
        val name=pokename
        val attackType1=typ1
        val attackType2=typ2
        val kp=Integer.valueOf(inputKp)
        val defense=Integer.valueOf(def)
        val attack=Integer.valueOf(att)
        val specialAttack=Integer.valueOf(sAtt)
        val specialDefense=Integer.valueOf(sDef)
        val speed=Integer.valueOf(inSpeed)
    }
}

fun addNewPokémon (name: String): Pokémon{
    val newPokémon=Pokémon()
    val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv")
    getMonsterbyName(monsterDB,name, newPokémon)
    return newPokémon
}