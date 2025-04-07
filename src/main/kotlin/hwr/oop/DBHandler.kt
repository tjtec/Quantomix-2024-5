package hwr.oop

import java.io.File
import java.lang.Integer.parseInt

class DBHandler {
    fun getMonsterbyName(file: File, Name: String): Boolean {
        val seperator = ",";
        var searchSuccessful = false;
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
                    println("name:" + block[0])
                    println("attackType1:" + block[1]);
                    println("attackType2:" + type2);
                    println("kp:" + block[3]);
                    println("attack:" + block[4]);
                    println("defense:" + block[5]);
                    println("specialAttack:" + block[6]);
                    println("specialDefense:" + block[7]);
                    println("speed:" + block[8]);
                    searchSuccessful = true;
                }
            }
        } catch (e: Exception) {
            println("Fehler beim Lesen der Datei: ${e.localizedMessage}")
        }
        return searchSuccessful;
    }
}
