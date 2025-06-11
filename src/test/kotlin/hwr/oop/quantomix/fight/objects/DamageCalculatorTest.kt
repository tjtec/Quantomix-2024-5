package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.fight.logic.DamageCalculator
import hwr.oop.quantomix.fight.logic.DamageContext
import hwr.oop.quantomix.fight.logic.ModeOfDamageCalculation
import hwr.oop.quantomix.fight.logic.SimpleBattle
import hwr.oop.quantomix.fight.logic.StandardDamageStrategy
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class DamageCalculatorTest : AnnotationSpec() {
  @BeforeEach
  fun feuerball(): Attack {
    return Attack(
      attackName = "Feuerball",
      type = Typ.Feuer,
      damage = 80,
      damageQuote = 100,
      specialAttack = true,
    )
  }

  @BeforeEach
  fun feuersturm(): Attack {
    return Attack(
      attackName = "Feuersturm",
      type = Typ.Feuer,
      damage = 80,
      damageQuote = 100,
      specialAttack = false,
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
      attacks = listOf(feuerball(), feuersturm())
    ).newBattleStats()
  }

  @BeforeEach
  fun flamara2(): BattleStats {
    return Quantomix(
      quantomixName = "Flamara2",
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
      attacks = listOf(feuerball(), feuersturm())
    ).newBattleStats()
  }

  @Test
  fun `correct  damage with specialAttack and self hit`() {
    val attacker = flamara()
    val target = flamara2()
    val context = DamageContext(
      attacker = attacker,
      target = target,
      attack = feuerball(),
      selfHitMultiplier = -1
    )
    val damage = DamageCalculator.calculateDamage(
      context,
      mode = ModeOfDamageCalculation.Simple
    )
    assertThat(damage).isEqualTo(28)
  }

  @Test
  fun `correct  damage with Attack and self hit`() {
    val attacker = flamara()
    val target = flamara2()
    val context = DamageContext(
      attacker = attacker,
      target = target,
      attack = feuersturm(),
      selfHitMultiplier = -1
    )
    val damage = DamageCalculator.calculateDamage(
      context,
      mode = ModeOfDamageCalculation.Simple
    )
    assertThat(damage).isEqualTo(78)
  }

  @Test
  fun `maxDamage less than 0 and no attack damage but Status effect`() {
    val target=flamara2()
    val attack = Attack(
      attackName = "maxDamage 0",
      type = Typ.Feuer,
      damage = 1,
      damageQuote = 100,
      specialAttack = false,
    )
    var context = DamageContext(
      attacker = flamara(),
      target = target,
      attack = attack,
      selfHitMultiplier = -1
    )
    var damage = DamageCalculator.calculateDamage(
      context,
      mode = ModeOfDamageCalculation.Simple
    )
    assertThat(damage).isEqualTo(0)
    target.changeStatus(Status.Poison)
    context = DamageContext(
      attacker = flamara(),
      target = target,
      attack = attack,
      selfHitMultiplier = -1
    )
    damage = DamageCalculator.calculateDamage(
      context,
      mode = ModeOfDamageCalculation.Simple
    )
    assertThat(damage).isEqualTo(6)
  }

  @Test
  fun `correct damage with random damage`() {
    val feelinara = Quantomix(
      quantomixName = "Feelinara",
      typ1 = Typ.Fee,
      typ2 = null,
      stats = Stats(
        kp = 65,
        attack = 100,
        defense = 100,
        specialAttack = 100,
        specialDefense = 100,
        speed = 100
      ),
      attacks = listOf(feuerball(), feuersturm())
    ).newBattleStats()
    var attack = Attack(
      attackName = "damageQuote",
      type = Typ.Feuer,
      damage = 3,
      damageQuote = 100,
      specialAttack = false,
    )
    var context = DamageContext(
      attacker = flamara2(),
      target = feelinara,
      attack = attack,
    )
    var damage =
      DamageCalculator.calculateDamage(context, ModeOfDamageCalculation.Complex)
    assertThat(damage).isLessThanOrEqualTo(1)
    attack = Attack(
      attackName = "damageQuote",
      type = Typ.Feuer,
      damage = 5,
      damageQuote = 100,
      specialAttack = true,
    )
    context = DamageContext(
      attacker = flamara2(),
      target = feelinara,
      attack = attack,
    )
    damage =
      DamageCalculator.calculateDamage(context, ModeOfDamageCalculation.Complex)
    assertThat(damage).isLessThanOrEqualTo(4)
    assertThat(damage).isBetween(1, 4)
  }
  @Test
  fun `status summand and multiplier makes the correct damage`(){
    val attackPoisoning=Attack(
      attackName = "Poisoning",
      type = Typ.Normal,
      damage = 3,
      damageQuote = 100,
      specialAttack = false,
      status = Status.Poison
    )
    val flamara=flamara()
    val flamara2=flamara2()
    SimpleBattle().simpleBattle(
      aktiveQuantomixBattleStats = flamara,
      attack = attackPoisoning,
      target = flamara2,
      attackStrategy = StandardDamageStrategy()
    )
    flamara2.changeStatus(Status.Poison)
    var context= DamageContext(
      attacker = flamara,
      target = flamara2,
      attack = attackPoisoning,
    )
    var damage=DamageCalculator.calculateDamage(
      context = context,
      mode = ModeOfDamageCalculation.Simple
    )
    assertThat(damage).isEqualTo(1+(flamara2.getStats().getKp()/16))
    flamara.changeStatus(Status.Sleep)
    context= DamageContext(
      attacker = flamara,
      target = flamara2,
      attack = attackPoisoning,
    )
    damage=DamageCalculator.calculateDamage(
      context = context,
      mode = ModeOfDamageCalculation.Simple
    )
    assertThat(damage).isEqualTo(0)
  }
}