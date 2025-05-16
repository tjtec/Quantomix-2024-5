package hwr.oop.quantomix.monster

import hwr.oop.quantomix.fight.logic.BattleStats
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.objects.Typ

class Quantomix(
    val quantomixName: String,
    val typ1: Typ,
    val typ2: Typ?,
    val stats: Stats,
    val attacks: List<Attack>,
    var battleStats: BattleStats = BattleStats(stats.deepCopy())
)

