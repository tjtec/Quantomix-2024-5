package hwr.oop

import java.io.File
import java.lang.Integer.parseInt

class DBHandler {

    fun createQuantomixObject(file: File, Name: String): Quantomix {
        val seperator = ",";
        var searchSuccessful = false;
        var returnArray: List<String>? = null;
        try {
            file.forEachLine { line ->
                val block = line.split(seperator).toList();
                val mName = block[0].toList();
                if (block[0] == Name) {
                    var type2 = block[2].toString();
                    if (type2 == "") {
                        type2 = "none";
                    }
                    //ALL Values for Entity
                    returnArray = listOf(
                        block[0],
                        block[1],
                        type2,
                        block[3],
                        block[4],
                        block[5],
                        block[6],
                        block[7],
                        block[8]
                    );
                    searchSuccessful = true;
                }
            }
        } catch (e: Exception) {
            //println("Fehler beim Lesen der Datei: ${e.localizedMessage}")
            error("Fehler beim Lesen der Datei")
        }
        if (returnArray != null) {
            var returnQuantomix: Quantomix = Quantomix(
                returnArray!![0],
                returnArray!![1],
                returnArray!![2],
                parseInt(returnArray!![3]),
                parseInt(returnArray!![4]),
                parseInt(returnArray!![5]),
                parseInt(returnArray!![6]),
                parseInt(returnArray!![7]),
                parseInt(returnArray!![8])
            );
            //print(returnArray.toString());
            return returnQuantomix;
        }
        else {
            error("No data found for file");
        }
    }
}
//fun main(){
//    val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
//    val temp_quantomix = DBHandler().createQuantomixObject(monsterDB, "Schlatompfe");
//    println(temp_quantomix.quantomixName);
//    println(temp_quantomix.typ1);
//    println(temp_quantomix.typ2);
//    println(temp_quantomix.inputKp);
//    println(temp_quantomix.attack);
//    println(temp_quantomix.defense);
//    println(temp_quantomix.specialAttack);
//    println(temp_quantomix.specialDefense);
//    println(temp_quantomix.speed);
//}
