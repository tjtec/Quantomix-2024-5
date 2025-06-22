package hwr.oop.quantomix.fight

import hwr.oop.quantomix.Exceptions.DeadQuantomixException
import hwr.oop.quantomix.fight.logic.DamageStrategy
import hwr.oop.quantomix.fight.logic.Round
import hwr.oop.quantomix.fight.logic.StandardDamageStrategy
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals

class BattleTests : AnnotationSpec() {
  @BeforeEach
  fun glurak(): Quantomix {
    val glurak = Quantomix(
      quantomixName = "Glurak",
      typ1 = Typ.Feuer,
      typ2 = Typ.Flug,
      stats = Stats(
        kp = 78,
        attack = 84,
        defense = 78,
        specialAttack = 109,
        specialDefense = 85,
        speed = 100
      ),
      attacks = listOf(
        tackle(), pfluecker(), glut(), spukball()
      )
    )
    return glurak
  }

  @BeforeEach
  fun pfluecker(): Attack {
    val pfluecker = Attack(
      attackName = "Pflücker",
      type = Typ.Flug,
      damage = 20,
      damageQuote = 100,
      specialAttack = true,
      effects = mutableListOf(),
      status = null
    )
    return pfluecker
  }

  @BeforeEach
  fun tackle(): Attack {
    val tackle = Attack(
      attackName = "Tackle",
      type = Typ.Normal,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(),
      status = null
    )
    return tackle
  }

  @BeforeEach
  fun glut(): Attack {
    val glut = Attack(
      attackName = "Glut",
      type = Typ.Feuer,
      damage = 40,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(),
      status = null
    )
    return glut
  }

  @BeforeEach
  fun feuerball(): Attack {
    val glut = Attack(
      attackName = "Feuerball",
      type = Typ.Feuer,
      damage = 100,
      damageQuote = 100,
      specialAttack = false,
      effects = mutableListOf(),
      status = null
    )
    return glut
  }

  @BeforeEach
  fun spukball(): Attack {
    val fruststampfer = Attack(
      attackName = "Fruststampfer",
      type = Typ.Geist,
      damage = 40,
      damageQuote = 100,
      specialAttack = true,
      effects = mutableListOf(),
      status = null
    )
    return fruststampfer
  }

  @BeforeEach
  fun schillok(): Quantomix {
    val schillok = Quantomix(
      quantomixName = "Schillok",
      typ1 = Typ.Wasser,
      typ2 = null,
      stats = Stats(
        kp = 59,
        attack = 63,
        defense = 80,
        specialAttack = 65,
        specialDefense = 80,
        speed = 58
      ),
      attacks = listOf(tackle())
    )
    return schillok
  }

  @BeforeEach
  fun owei(): Quantomix {
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
      attacks = listOf(glut())
    )
    return owei
  }

  @BeforeEach
  fun rattzfatz(): Quantomix {
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
      attacks = listOf(tackle())
    )
    return rattzfatz
  }

  @BeforeEach
  fun damageStrategy(): DamageStrategy {
    val damageFunction: DamageStrategy = StandardDamageStrategy()
    return damageFunction
  }

  @Test
  fun `BattleTest with normal Attack`() {
    val glurak = glurak()
    val glurakBattleStats = glurak.newBattleStats()
    val schillokBattleStats = schillok().newBattleStats()
    val battle = Round()
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = glurakBattleStats,
      attack = tackle(),
      target = schillokBattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(solution).isEqualTo(true)
    assertThat(
      schillokBattleStats.getStats().getKp()
    ).isLessThan(schillok().getStats().getKp())
    assertThat(schillokBattleStats.getStats().getKp()).isEqualTo(26)
    assertThat(schillok().getStats().getKp()).isEqualTo(59)
  }

  @Test
  fun `BattleTest with special Attack Type 2`() {
    val glurak = glurak()
    val glurakBattleStats = glurak.newBattleStats()
    val schillok = schillok()
    val schillokBattleStats = schillok.newBattleStats()
    val battle = Round()
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = glurakBattleStats,
      attack = pfluecker(),
      target = schillokBattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(solution).isEqualTo(true)
    assertThat(
      schillokBattleStats.getStats().getKp()
    ).isLessThan(schillok().getStats().getKp())
    assertThat(schillok.getStats().getKp()).isEqualTo(59)
    assertThat(schillokBattleStats.getStats().getKp()).isEqualTo(38)
  }

  @Test
  fun `BattleTest extrem effectiviness and less effectiviness`() {
    val glurak = glurak()
    val glurakBattleStats = glurak.newBattleStats()
    val owei = owei()
    val oweiBattleStats = owei.newBattleStats()
    val battle = Round()
    assertThat(glurakBattleStats.getStats().getKp()).isEqualTo(
      glurak.getStats().getKp()
    )
    assertThat(oweiBattleStats.getStats().getKp()).isEqualTo(
      owei.getStats().getKp()
    )
    assertThat(
      glurakBattleStats.getStats().getAttack()
    ).isEqualTo(glurak.getStats().getAttack())
    assertThat(
      oweiBattleStats.getStats().getDefense()
    ).isEqualTo(owei.getStats().getDefense())
    assertThat(
      glurakBattleStats.getStats().getSpeed()
    ).isEqualTo(glurak.getStats().getSpeed())
    assertThat(oweiBattleStats.getStats().getSpeed()).isEqualTo(
      owei.getStats().getSpeed()
    )
    assertThat(
      glurakBattleStats.getStats().getSpecialAttack()
    ).isEqualTo(glurak.getStats().getSpecialAttack())
    assertThat(
      oweiBattleStats.getStats().getSpecialAttack()
    ).isEqualTo(owei.getStats().getSpecialAttack())
    assertThat(glurakBattleStats.getStats().getSpecialDefense()).isEqualTo(
      glurak.getStats().getSpecialDefense()
    )
    assertThat(
      oweiBattleStats.getStats().getSpecialDefense()
    ).isEqualTo(owei.getStats().getSpecialDefense())
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = glurakBattleStats,
      attack = pfluecker(),
      target = oweiBattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(solution).isEqualTo(true)
    assertThat(
      oweiBattleStats.getStats().getKp()
    ).isLessThan(schillok().getStats().getKp())
    assertThat(owei.getStats().getKp()).isEqualTo(60)
    assertThat(oweiBattleStats.getStats().getKp()).isEqualTo(17)
    val solution2 = battle.startAttack(
      aktiveQuantomixBattleStats = oweiBattleStats,
      attack = pfluecker(),
      target = glurakBattleStats,
      attackStrategy = damageStrategy(),
    )
    assertThat(solution2).isEqualTo(true)
    assertThat(
      glurakBattleStats.getStats().getKp()
    ).isLessThan(glurak.getStats().getKp())
    assertThat(glurakBattleStats.getStats().getKp()).isEqualTo(62)
    assertThat(
      oweiBattleStats.getStats().getKp()
    ).isLessThan(schillok().getStats().getKp())
    assertThat(oweiBattleStats.getStats().getKp()).isEqualTo(17)
  }

  @Test
  fun `BattleTest not effective`() {
    val rattzfatz = rattzfatz()
    val rattzfatzBattleStats = rattzfatz.newBattleStats()
    val glurak = glurak()
    val glurakBattleStats = glurak.newBattleStats()
    val battle = Round()
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = glurakBattleStats,
      attack = spukball(),
      target = rattzfatzBattleStats,
      attackStrategy = damageStrategy()
    )
    assertThat(solution).isEqualTo(true)
    assertThat(
      glurakBattleStats.getStats().getKp()
    ).isEqualTo(glurak.getStats().getKp())
    assertThat(
      rattzfatzBattleStats.getStats().getKp()
    ).isEqualTo(rattzfatz().getStats().getKp())
  }

  @Test
  fun `BattleTest second Quantomix dead`() {
    val glurak = glurak()
    val owei = owei()
    val oweiBattleStats = owei.newBattleStats()
    val glurakBattleStats = glurak.newBattleStats()
    val battle = Round()
    val solution = battle.startAttack(
      aktiveQuantomixBattleStats = glurakBattleStats,
      attack = feuerball(),
      target = oweiBattleStats,
      attackStrategy = damageStrategy()
    )
    assertThat(solution).isEqualTo(false)
    assertThat(glurakBattleStats.getStats().getKp()).isEqualTo(78)
    assertThat(glurak.getStats().getKp()).isEqualTo(78)
    assertThat(oweiBattleStats.getStats().getKp()).isEqualTo(0)
    assertThat(owei.getStats().getKp()).isEqualTo(60)
  }

  @Test
  fun `BattleTest dead Quantomix want to attack`() {
    val glurak = glurak()
    val owei = owei()
    val oweiBattleStats = owei.newBattleStats()
    val glurakBattleStats = glurak.newBattleStats()
    val battle = Round()
    battle.startAttack(
      aktiveQuantomixBattleStats = glurakBattleStats,
      attack = glut(),
      target = oweiBattleStats,
      attackStrategy = damageStrategy()
    )
    try {
      battle.startAttack(
        aktiveQuantomixBattleStats = glurakBattleStats,
        attack = glut(),
        target = oweiBattleStats,
        attackStrategy = damageStrategy()
      )
    } catch (e: DeadQuantomixException) {
      assertEquals(
        "The Quantomix which would attack next is already dead!",
        e.message
      )
    }
  }
}
