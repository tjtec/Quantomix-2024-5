package hwr.oop

import java.io.File
import java.lang.Integer.parseInt

class DBHandler {
    // ToDo: Überlegen ob diese Funktion noch nötig ist
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
    //ToDo: Arrays eleminieren
    fun getMonsterbyNameObject(file: File, Name: String): Quantomix {
        val seperator = ",";
        var searchSuccessful = false;
        var returnArray: Array<String>? = null;
        try {
            file.forEachLine { line ->
                val block = line.split(seperator);
                val mName = block[0];
                if (block[0] == Name) {
                    var type2 = block[2].toString();
                    if (type2 == "") {
                        type2 = "none";
                    }
                    //ALL Values for Entity
                    returnArray = arrayOf<String>(
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
