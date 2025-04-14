package hwr.oop

import java.io.File
import java.lang.Integer.parseInt

class DBHandler {

    fun getMonsterbyNameObject(file: File, Name: String): Quantomix {
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
            error("Fehler beim Lesen der Datei: ${e.localizedMessage}")
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
            println(returnQuantomix.toString());
            return returnQuantomix;
        }
        else {
            error("No data found for file ${file.absolutePath}");
        }
    }
}
fun main(){
    val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
    println(DBHandler().getMonsterbyNameObject(monsterDB, "Glurak").toString());
}
