package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class EffectsTest: AnnotationSpec() {
  @BeforeEach
  fun stats():Stats{
    return Stats(
      kp = 40,
      attack = 3,
      defense = 2,
      specialAttack = 1,
      specialDefense = 6,
      speed = 5
    )
  }
  @BeforeEach
  fun effects1():Effects{
    return Effects(
      heal = true,
      changeStats = stats(),
      self=false
    )
  }
  @BeforeEach
  fun effects2():Effects{
    return Effects(
      heal = true,
      changeStats = stats(),
      self=true
    )
  }
  @BeforeEach
  fun targetBattleStats():BattleStats {
      return Quantomix(
        quantomixName = "Liminip",
        typ1 = Typ.Pflanze,
        typ2 = null,
        stats = Stats(
          kp = 50,
          attack = 50,
          defense = 50,
          specialAttack = 50,
          specialDefense = 50,
          speed = 50
        ),
        attacks = listOf(
          Attack(
            attackName = "Solarstrahl",
            damage = 50,
            damageQuote = 50,
            type = Typ.Pflanze,
            specialAttack = false,
            effects = mutableListOf(),
            status = null
          )
        ),
      ).newBattleStats()
  }
  @Test
  fun `Test buffsAndDebuffs`(){
    val battleStats: BattleStats = targetBattleStats()
    effects1().buffsAndDebuffs(battleStats)
    assertThat(battleStats.getStats().getKp()).isEqualTo(90)
    assertThat(battleStats.getStats().getAttack()).isEqualTo(125)
    assertThat(battleStats.getStats().getDefense()).isEqualTo(100)
    assertThat(battleStats.getStats().getSpecialAttack()).isEqualTo(75)
    assertThat(battleStats.getStats().getSpecialDefense()).isEqualTo(200)
    assertThat(battleStats.getStats().getSpeed()).isEqualTo(175)
  }
  @Test
  fun `Test getSelf`(){
    var self=effects1()
    val solutionSelf=self.hitsAttacker()
    assertThat(solutionSelf).isFalse
    self=effects2()
    val solutionSelf2=self.hitsAttacker()
    assertThat(solutionSelf2).isTrue
  }
}