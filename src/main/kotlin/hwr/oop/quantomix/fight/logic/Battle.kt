package hwr.oop.quantomix.fight.logic
//
//import hwr.oop.quantomix.DBHandler
//import hwr.oop.quantomix.stats.GameData
//import hwr.oop.quantomix.fight.objects.Attack
//import hwr.oop.quantomix.fight.objects.Effektivitaet
//import hwr.oop.quantomix.monster.Quantomix
//
//class Battle(private var quantomixA: Quantomix, private var quantomixB: Quantomix) {
//
//    private fun effectivity(attack: Attack): Double {
//        val effDB = GameData().effDB;
//        print("${otherAttacker().quantomixName}")
//        val effictivity1 = when (DBHandler().getData(
//            effDB,
//            Effektivitaet().Klassen.get(otherAttacker().typ1.name)!!,
//            Effektivitaet().Klassen.get(attack.type)!!
//        )[0]) {
//            "+" -> 2.0
//            "-" -> 1.0
//            "0" -> 0.5
//            "x" -> 0.0
//            else -> throw IllegalArgumentException("Ungültiges Symbol")
//        }
//        var effictivity2 = 0.0
//        if (otherAttacker().typ2 != null) {
//            effictivity2 = when ((DBHandler().getData(
//
//                effDB,
//                Effektivitaet().Klassen.get(otherAttacker().typ2!!.name)!!,
//                Effektivitaet().Klassen.get(attack.type)!!
//            )[0])) {
//                "+" -> 2.0
//                "-" -> 1.0
//                "0" -> 0.5
//                "x" -> 0.0
//                else -> throw IllegalArgumentException("Ungültiges Symbol")
//            }
//        }
//        return if (effictivity1 * effictivity2 == 1.0) {
//            effictivity1 * effictivity2
//        } else if (effictivity1 > effictivity2) {
//            effictivity1
//        } else {
//            effictivity2
//        }
//    }