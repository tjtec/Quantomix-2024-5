package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BattleStatsTest : AnnotationSpec() {
  @BeforeEach
  fun pikatchu(): Quantomix {
    return Quantomix(
      quantomixName = "pikatchu",
      stats = Stats(
        kp = 50,
        attack = 50,
        defense = 50,
        specialAttack = 50,
        specialDefense = 50,
        speed = 50
      ),
      typ1 = Typ.Elektro,
      attacks = listOf(
        Attack(
          attackName = "Donner",
          damage = 100,
          damageQuote = 50,
          specialAttack = true,
          type = Typ.Elektro,
        )
      ),
      typ2 = null,
    )
  }

  @Test
  fun `Test newKp`() {
    val battleStats = pikatchu().newBattleStats()
    battleStats.takeDamage(40)
    assertThat(battleStats.getStats().getKp()).isEqualTo(10)
  }

  @Test
  fun `Test changeStatus`() {
    val battleStats = pikatchu().newBattleStats()
    var success = battleStats.changeStatus(Status.StrongPoison)
    assertThat(success).isTrue
    success = battleStats.changeStatus(Status.Poison)
    assertThat(success).isFalse
  }

  @Test
  fun `Test changesAccordingToStatus`() {
    val battleStats = pikatchu().newBattleStats()
    battleStats.changeStatus(Status.StrongPoison)
    battleStats.changesAccordingToStatus()
    assertThat(battleStats.getStats().getKp()).isEqualTo(47)
    battleStats.changesAccordingToStatus()
    assertThat(battleStats.getStats().getKp()).isEqualTo(41)
    battleStats.changesAccordingToStatus()
    assertThat(battleStats.getStats().getKp()).isEqualTo(29)
    battleStats.changesAccordingToStatus()
    assertThat(battleStats.getStats().getKp()).isEqualTo(5)
    battleStats.changesAccordingToStatus()
    assertThat(battleStats.getStats().getKp()).isEqualTo(0)
  }

  @Test
  fun `Test getQuantomix`() {
    val pikatchu = pikatchu()
    val battleStats = pikatchu.newBattleStats()
    assertThat(battleStats.getQuantomix()).isEqualTo(pikatchu)
  }

  @Test
  fun `Test isAlive`() {
    val battleStats = pikatchu().newBattleStats()
    assertThat(battleStats.isAlive()).isEqualTo(true)
  }

  @Test
  fun `has Status`(){
    val battleStats = pikatchu().newBattleStats()

  }
}