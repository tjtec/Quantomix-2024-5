package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.Exceptions.NoBuffOrDebuffValue
import hwr.oop.quantomix.fight.logic.SimpleBattle
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertThrows

class AttackTest : AnnotationSpec() {
  @BeforeEach
  fun giftmuell(): Attack {
    val attack = Attack(
      attackName = "Giftmüll",
      type = Typ.Gift,
      damage = 50,
      damageQuote = 50,
      specialAttack = true,
      effects = mutableListOf(
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 10,
            attack = 4,
            defense = 3,
            specialAttack = 2,
            specialDefense = 1,
            speed = 0
          ),
          self = true
        )
      ),
      status = Status.StrongPoison
    )
    return attack
  }
  @BeforeEach
  fun fluch(): Attack {
    val attack = Attack(
      attackName = "Fluch",
      type = Typ.Geist,
      damage = 50,
      damageQuote = 50,
      specialAttack = true,
      effects = mutableListOf(
        Effects(
          heal = false,
          changeStats = Stats(
            kp = 10,
            attack = 4,
            defense = 3,
            specialAttack = 2,
            specialDefense = 1,
            speed = 0
          ),
          self = false
        )
      ),
    )
    return attack
  }
  @BeforeEach
  fun attacker():Quantomix {
    return Quantomix(
      quantomixName = "Slima",
      typ1 = Typ.Gift,
      typ2 = null,
      stats = Stats(
        kp = 10,
        attack = 10,
        defense = 20,
        specialAttack = 10,
        specialDefense = 20,
        speed = 10
      ),
      attacks = listOf(giftmuell(), fluch())
    )
  }
  @BeforeEach
  fun target(): Quantomix{
    return Quantomix(
      quantomixName = "Slima",
      typ1 = Typ.Gift,
      typ2 = null,
      stats = Stats(
        kp = 10,
        attack = 10,
        defense = 20,
        specialAttack = 10,
        specialDefense = 20,
        speed = 10
      ),
      attacks = listOf(giftmuell(),fluch())
    )
  }
@Test
  fun `Test changeStatsAndStatus`() {
    val attack = giftmuell()
    val attacker = attacker()
    val target = target()
    val attackerBattleStats = attacker.newBattleStats()
    val targerBattleStats = target.newBattleStats()
  attack.changeStatsAndStatus(
    attacker = attackerBattleStats,
    target = targerBattleStats
  )
    assertThat(targerBattleStats.hasStatus()).isTrue()
  assertThat(attackerBattleStats.getStats().getKp()).isLessThan(attacker.getStats().getKp())
  val attackFluch=fluch()
  attackFluch.changeStatsAndStatus(attacker = targerBattleStats,
    target=attackerBattleStats)
  assertThat(targerBattleStats.getStats().getKp()).isEqualTo(target.getStats().getKp())
  assertThat(attackerBattleStats.hasStatus()).isFalse()
  }
@Test
  fun `Test attack hits`() {
    val attack = giftmuell()
    assertThat(attack.hits(50)).isTrue
  assertThat(attack.hits(51)).isFalse
  }
@Test
  fun `Test attack has status`() {
    assertThat(giftmuell().hasStatus()).isTrue
  assertThat(fluch().hasStatus()).isFalse
  }
@Test
  fun `Test getter of attack`() {
    val attack = giftmuell()
    assertThat(attack.getType()).isEqualTo(Typ.Gift)
    assertThat(attack.getDamage()).isEqualTo(50)
    assertThat(attack.getSpecialAttack()).isEqualTo(true)
    assertThat(attack.getDamageQuote()).isEqualTo(50)
    assertThat(attack.getEffects()).isNotEmpty
  }

}