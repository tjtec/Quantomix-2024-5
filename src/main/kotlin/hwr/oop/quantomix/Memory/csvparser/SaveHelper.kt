package hwr.oop.quantomix.Memory

import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach

class SaveHelper(
  val trainer1: Coach,
  val trainer2: Coach,
  val quantomixAndBattleStatsMap: MutableMap<Quantomix, BattleStats>,
)