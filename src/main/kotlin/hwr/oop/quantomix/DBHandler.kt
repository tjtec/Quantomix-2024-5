package hwr.oop.quantomix

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Effektivitaet
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import hwr.oop.quantomix.stats.GameData
import java.io.File
import java.lang.Integer.parseInt

class DBHandler {

    fun getData(file: File, column: Int = -1, row: Int = -1, query: String = ""): List<String> {
        val seperator = ",";
        var currentline = -1;
        var returnList: List<String>? = null;
        println("File: ${file.absolutePath}")
        try {
            file.forEachLine { line ->
                currentline += 1;
                val block = line.split(seperator).toList();
                if (column != -1 && query != "") { //Wenn eine Suchspalte und Suchkriterium angegeben wurde.
                    if (block[column] == query) {
                        returnList = block;
                    }
                } else if (row != -1 && column == -1) { // Wenn nur Zeile angegeben.
                    if (currentline == row) {
                        returnList = block;
                    }
                } else if (column != -1 && row != -1) { //Wenn Zeile und Spalte angegeben.
                    if (currentline == row) {
                        returnList = listOf(block[column]);
                    }
                } else {
                    error("Invalid Arguments.");
                }
            }
            return returnList!!;
        } catch (e: Exception) {
            println("Fehler beim Lesen der Datei: ${e.localizedMessage}")
            error("Fehler beim Lesen der Datei")
        }
    }

    fun createQuantomixObject(file: File, name: String): Quantomix {
        var block = getData(file, 0, -1, name);
        var returnArray: List<String>? = null;
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
            block[8],
            block[9],
            block[10],
            block[11],
            block[12]
        );
        if (returnArray != null) {
            var typ1 = Typ(returnArray[1])
            var typ2 = Typ(returnArray[2]);
            var returnQuantomix: Quantomix = Quantomix(
                returnArray[0],
                typ1,
                typ2,
                parseInt(returnArray[3]),
                parseInt(returnArray[4]),
                parseInt(returnArray[5]),
                parseInt(returnArray[6]),
                parseInt(returnArray[7]),
                parseInt(returnArray[8]),
                listOf(
                    createAttack(returnArray[9]),
                    createAttack(returnArray[10]),
                    createAttack(returnArray[11]),
                    createAttack(returnArray[12])
                )
            );
            //print(returnArray.toString());
            return returnQuantomix;
        } else {
            error("No data found for file");
        }
    }

    private fun createAttack(attackName: String): Attack {
        return Attack(attackName, null, null, null)
    }
}

fun main() {
    val monsterDB = GameData().monsterDB
    val effDB = GameData().effDB

    println("Create Quantomix Object");
    val temp_quantomix = DBHandler().createQuantomixObject(monsterDB, "Schlatompfe");
    println(temp_quantomix.quantomixName);
    println(temp_quantomix.typ1);
    println(temp_quantomix.typ2);
    println(temp_quantomix.kp);
    println(temp_quantomix.attack);
    println(temp_quantomix.defense);
    println(temp_quantomix.specialAttack);
    println(temp_quantomix.specialDefense);
    println(temp_quantomix.speed);

    println("Kampf Gegen Normal Effektivität");
    println(DBHandler().getData(effDB, Effektivitaet().Klassen.get("Flug")!!, Effektivitaet().Klassen.get("Kampf")!!));

    println("Return Line Array");
    println(DBHandler().getData(effDB, -1, 1, ""));
}