package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.DamageContext
import hwr.oop.quantomix.fight.logic.SimpleBattle
import hwr.oop.quantomix.fight.logic.StandardDamageStrategy
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class DamageContextTest : AnnotationSpec() {
  @BeforeEach
  fun feuerball(): Attack {
    return Attack(
      attackName = "Feuerball",
      type = Typ.Feuer,
      damage = 80,
      damageQuote = 100,
      specialAttack = true,
      status = Status.Sleep
    )
  }
  @BeforeEach
  fun feuerballWithoutPoison():Attack{
    return Attack(
      attackName = "Feuerball",
      type = Typ.Feuer,
      damage = 80,
      damageQuote = 100,
      specialAttack = true,
    )
  }
  @BeforeEach
  fun flamara(): BattleStats {
    return Quantomix(
      quantomixName = "Flamara",
      typ1 = Typ.Feuer,
      typ2 = null,
      stats = Stats(
        kp = 65,
        attack = 130,
        defense = 60,
        specialAttack = 95,
        specialDefense = 110,
        speed = 65
      ),
      attacks = listOf(feuerball())
    ).newBattleStats()
  }

  @BeforeEach
  fun flamara2(): BattleStats {
    return Quantomix(
      quantomixName = "Flamara",
      typ1 = Typ.Feuer,
      typ2 = null,
      stats = Stats(
        kp = 65,
        attack = 95,
        defense = 110,
        specialAttack = 130,
        specialDefense = 60,
        speed = 65
      ),
      attacks = listOf(feuerball())
    ).newBattleStats()
  }

  @Test
  fun `DamageContext works correctly with status`() {
    val attacker = flamara()
    val target = flamara2()
    var damageContext = DamageContext(
      attacker = attacker,
      target = target,
      attack = feuerball(),
    )
    assertThat(damageContext.statusEffect.summand).isEqualTo(
      0
    )
    SimpleBattle().simpleBattle(
      target = target,
      attack = feuerball(),
      aktiveQuantomixBattleStats = attacker,
      attackStrategy = StandardDamageStrategy(),
    )
    damageContext = DamageContext(
      attacker = attacker,
      target = target,
      attack = feuerball(),
    )
    assertThat(damageContext.statusEffect.summand).isEqualTo(
      target.getStats().getKp() / 16
    )
  }
  @Test
  fun `selfHitMultiplier works correctly`() {
    assertThat(DamageContext(
      attacker=flamara(),
      target = flamara2(),
      attack = feuerball(),
      selfHitMultiplier = 1
    ).selfHitMultiplier).isEqualTo(1)
    assertThat(DamageContext(
      attacker=flamara(),
      target = flamara2(),
      attack = feuerball(),
      selfHitMultiplier = -1
    ).selfHitMultiplier).isEqualTo(-1)
    assertThat(DamageContext(
      attacker=flamara(),
      target = flamara2(),
      attack = feuerball(),
    ).selfHitMultiplier).isEqualTo(0)
  }
  @Test
  fun `target and attack have no status effect`() {
    assertThat(DamageContext(
      attacker = flamara(),
      target = flamara2(),
      attack = feuerballWithoutPoison(),
    ).statusEffect.summand).isEqualTo(0)
    assertThat(DamageContext(
      attacker = flamara(),
      target = flamara2(),
      attack = feuerballWithoutPoison(),
    ).statusEffect.multiplicator).isEqualTo(1)
  }
  @Test
  fun `target has status effect`() {
    val flamara = flamara()
    flamara.changeStatus(Status.Combustion)
    assertThat(DamageContext(
      attacker = flamara2(),
      target = flamara,
      attack = feuerballWithoutPoison(),
    ).statusEffect.summand).isEqualTo(8)
    assertThat(DamageContext(
      attacker = flamara2(),
      target = flamara,
      attack = feuerballWithoutPoison(),
    ).statusEffect.multiplicator).isEqualTo(1)
  }
  @Test
  fun `attack has status effect`() {
    val flamara = flamara2()
    flamara.changeStatus(Status.Sleep)
    assertThat(DamageContext(
      attacker = flamara,
      target = flamara2(),
      attack = feuerball(),
    ).statusEffect.summand).isEqualTo(0)
    assertThat(DamageContext(
      attacker = flamara,
      target = flamara2(),
      attack = feuerball(),
    ).statusEffect.multiplicator).isEqualTo(0)
  }
}