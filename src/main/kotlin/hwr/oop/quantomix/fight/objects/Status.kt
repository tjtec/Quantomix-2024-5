package hwr.oop.quantomix.fight.objects

enum class Status {
    NoDamage,
    Poison,
    StrongPoison,
    Combustion,
    Paralysis,
    Sleep,
    Confusion,
    Freeze
}
//
//import hwr.oop.quantomix.fight.objects.BattleStats
//
//class Status(
//    var noDamage: Boolean = false,
//    var poisoning: Boolean = false,
//    var strongPoisoning: Boolean = false,
//    var combustion: Boolean = false,
//    var paralysis: Boolean = false,
//    var sleep: Boolean = false,
//    var confusion: Boolean = false,
//    var freeze: Boolean = false,
//) {
//    val duration: Int = (2..5).random()
//
//
//
//    fun effectsOfStatus(targetBattleStats: BattleStats, alreadyPassedRounds: Int) {
//        if (poisoning) {
//            targetBattleStats.stats.kp -= (targetBattleStats.stats.kp / 16)
//        }
//        if (strongPoisoning) {
//            targetBattleStats.stats.kp += (targetBattleStats.stats.kp / 16) * 2
//        }
//        if (combustion) {
//            targetBattleStats.stats.kp -= (targetBattleStats.stats.kp / 8)
//        }
//        if (paralysis) {
//            targetBattleStats.nextAttack!!.damageQuote = targetBattleStats.nextAttack!!.damageQuote * 2 / 3
//            targetBattleStats.stats.speed = targetBattleStats.stats.speed / 2
//        }
//        if (sleep || freeze) {
//            if (duration >= alreadyPassedRounds) {
//                if (targetBattleStats.target!!.battleStats.status == null) {
//                    targetBattleStats.target!!.battleStats.status = Status(true)
//                } else {
//                    targetBattleStats.target!!.battleStats.status!!.noDamage = true
//                }
//            }
//        }
//        if (confusion) {
//            //ToDo: Ich muss irgendwo festhalten, dass die Attacken in 50% der Fälle nach hinten los gehen, doch wo?
//        }
//    }
//}