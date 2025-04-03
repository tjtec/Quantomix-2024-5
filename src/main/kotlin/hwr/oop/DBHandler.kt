package hwr.oop

import java.io.File

fun readCsvData(filePath: String, delimiter: Char = ',', skipHeader: Boolean = false): List<List<String>>? {
    val file = File(filePath)

    if (!file.exists() || !file.canRead()) {
        println("Fehler: Datei nicht gefunden oder keine Leseberechtigung für '$filePath'")
        return null
    }
    val data = mutableListOf<List<String>>()
    try {
        file.useLines { lines ->
            lines.forEachIndexed { index, line ->
                if (skipHeader && index == 0) {
                    return@forEachIndexed
                }
                if (line.isBlank()) {
                    return@forEachIndexed
                }

                val rowData = line.split(delimiter).map { it.trim() }
                data.add(rowData)
            }
        }
        return data
    } catch (e: Exception) {
        println("Fehler beim Lesen der Datei '$filePath': ${e.message}")
        return null
    }
}
fun getMonsterbyName(MonsterDB: String, Name: String) {
    val csvContentMitHeader = readCsvData(MonsterDB, skipHeader = false)
    if (csvContentMitHeader != null) {
        csvContentMitHeader.forEachIndexed { rowIndex, row ->
            //println("Zeile ${rowIndex + 1}: $row")
            var block = row.toString().replace("[", "").replace("]", "").split(";");
            val mName = block[0];
            //println(mName);
            if(block[0] == Name){
                var type2 = block[2].toString();
                if(type2 == ""){
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
            }

        }
    } else {
        println("Konnte die CSV-Datei nicht lesen.")

    }
}

fun main(args: Array<String>) {
    val MonsterDBPath = "src/main/kotlin/hwr/oop/resources/test.csv";


    if(args.size > 0) { //Suche über Commandline args möglich
        getMonsterbyName(MonsterDBPath, args[0]);
    }
    else{ //Usecase ohne Commandline
        getMonsterbyName(MonsterDBPath, "Glurak");
    }




}