package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.StandardDamageStrategy
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class DamageTest : AnnotationSpec() {
  @BeforeEach
  fun attack(): Attack {
    val attack = Attack(
      attackName = "Testattack",
      type = Typ.Normal,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
    )
    return attack
  }

  @BeforeEach
  fun quantomix(): Quantomix {
    val quantomix = Quantomix(
      quantomixName = "Test Quantomix",
      stats = Stats(
        kp = 50,
        attack = 50,
        defense = 50,
        specialAttack = 50,
        specialDefense = 50,
        speed = 50
      ),
      typ1 = Typ.Normal,
      typ2 = null,
      attacks = listOf(attack())
    )
    return quantomix
  }

  @BeforeEach
  fun battleStats(): BattleStats {
    return quantomix().newBattleStats()
  }

  @Test
  fun `Test no exception is thrown`() {
    val damage = StandardDamageStrategy()
    val currentDamage = damage.damageFunction(
      attacker = battleStats(),
      attack = attack(),
      target = battleStats(),
    )
    assertThat(currentDamage).isEqualTo(20)
  }
}