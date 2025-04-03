package hwr.oop

import java.io.File

fun getMonsterbyName(file: File, Name: String, monster: Pokemon) {
    val seperator = ",";
    try {
        file.forEachLine { line ->
            val block = line.split(seperator);
            val mName = block[0];
            //println(mName);
            if (block[0] == Name) {
                var type2 = block[2].toString();
                if (type2 == "") {
                    type2 = "none";
                }
                //ALL Values for Entity
                monster.setPokemonVariables(block[0],block[1],block[2],block[3],block[4],block[5],block[6], block[7], block[8]);
//                println("name:" + block[0])
//                println("attackType1:" + block[1]);
//                println("attackType2:" + type2);
//                println("kp:" + block[3]);
//                println("attack:" + block[4]);
//                println("defense:" + block[5]);
//                println("specialAttack:" + block[6]);
//                println("specialDefense:" + block[7]);
//                println("speed:" + block[8]);
            }
        }
    } catch (e: Exception) {
        println("Fehler beim Lesen der Datei: ${e.localizedMessage}")
    }
}

//fun main(args: Array<String>) {
//    val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
//
//    if (monsterDB.exists()) {
//        if(args.size > 0) { //Suche über Commandline args möglich
//            //getMonsterbyName(monsterDB, args[0]);
//        }
//        else{ //Usecase ohne Commandline
//            //getMonsterbyName(monsterDB, "Glurak");
//        }
//    } else {
//        println("Die Datei '${monsterDB.name}' wurde nicht gefunden.")
//    }
//}