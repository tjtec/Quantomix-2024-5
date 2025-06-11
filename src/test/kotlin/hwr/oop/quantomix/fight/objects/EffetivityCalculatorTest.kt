package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.DamageContext
import hwr.oop.quantomix.fight.logic.EffectivityCalculator
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class EffetivityCalculatorTest : AnnotationSpec() {
  @BeforeEach
  fun attack(): Attack {
    val attack = Attack(
      attackName = "Testattack",
      type = Typ.Geist,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
      status = Status.Confusion
    )
    return attack
  }

  @BeforeEach
  fun quantomixBattleStats(): BattleStats {
    return Quantomix(
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
      attacks = listOf(attack()
      )
    ).newBattleStats()
  }
  @BeforeEach
  fun quantomixToTypesBattleStats(): BattleStats {
    return Quantomix(
      quantomixName = "Test two types",
      stats = Stats(
        kp = 50,
        attack = 50,
        defense = 50,
        specialAttack = 50,
        specialDefense = 50,
        speed = 50
      ),
      typ1 = Typ.Normal,
      typ2 = Typ.Geist,
      attacks = listOf(attack()
      )
    ).newBattleStats()
  }
  @BeforeEach
  fun context(): DamageContext {
    return DamageContext(
      attacker = quantomixBattleStats(),
      target = quantomixBattleStats(),
      attack = attack(),
      selfHitMultiplier = 2
    )
  }
  @BeforeEach
  fun context2Types(): DamageContext {
    return DamageContext(
      attacker = quantomixToTypesBattleStats(),
      target = quantomixBattleStats(),
      attack = attack(),
      selfHitMultiplier = 2
    )
  }
  @Test
  fun `right calculated effetivity with self hit`() {
    assertThat(EffectivityCalculator.calculateEffectivity(context())).isEqualTo(0.0f)
    assertThat(EffectivityCalculator.calculateEffectivity(context2Types())).isEqualTo(2.0f)
  }
}