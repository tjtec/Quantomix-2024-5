package hwr.oop.quantomix.memory

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class GameStateTest : AnnotationSpec() {

  @Test
  fun `Test Save and Load`() {
    val tackle = Attack(
      attackName = "Tackle",
      type = Typ.Normal,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(),
      status = null
    )
    val glut = Attack(
      attackName = "Glut",
      type = Typ.Feuer,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(),
      status = null
    )
    val owei = Quantomix(
      quantomixName = "Owei",
      typ1 = Typ.Pflanze,
      typ2 = Typ.Psycho,
      stats = Stats(
        kp = 60,
        attack = 40,
        specialAttack = 80,
        defense = 60,
        specialDefense = 45,
        speed = 40
      ),
      attacks = listOf(glut)
    )
    val rattzfatz = Quantomix(
      quantomixName = "Rattzfatz",
      typ1 = Typ.Normal,
      typ2 = null,
      stats = Stats(
        kp = 55,
        attack = 81,
        specialAttack = 60,
        defense = 50,
        specialDefense = 70,
        speed = 97
      ),
      attacks = listOf(tackle)
    )
    var battleStatsMap = mutableMapOf<Quantomix, BattleStats>()
    battleStatsMap.getOrPut(rattzfatz) { rattzfatz.newBattleStats() }
    battleStatsMap.getOrPut(owei) { owei.newBattleStats() }
    val trainer1 = Coach(
      coachName = "${(0..99999999999999).random()}",
      quantomixTeam = listOf(owei, rattzfatz)
    )
    val trainer2 = Coach(
      coachName = "${(0..99999999999999).random()}",
      quantomixTeam = listOf(rattzfatz, owei)
    )

    Save(trainer1, trainer2, battleStatsMap)
    val (loadedTrainer1, loadedTrainer2, loadedBattleStatsMap) = Load()

    Assertions.assertThat(loadedTrainer1?.coachName)
      .isEqualTo(trainer1.coachName)
    Assertions.assertThat(loadedTrainer2?.coachName)
      .isEqualTo(trainer2.coachName)
    Assertions.assertThat(loadedTrainer1?.quantomixTeam?.get(1)?.getType1())
      .isEqualTo(trainer1.quantomixTeam.get(1).getType1())
    Assertions.assertThat(filePath).isEqualTo("game_state.json")
    Assertions.assertThat(loadedBattleStatsMap?.size)
      .isEqualTo(battleStatsMap.size)
    Assertions.assertThat(loadedBattleStatsMap?.size).isEqualTo(2)
  }
}