package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.Exceptions.NoBuffOrDebuffValue
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

class TestStats : AnnotationSpec() {
  @BeforeEach
  fun stats1(): Stats {
    return Stats(
      kp = 30,
      attack = 30,
      defense = 30,
      specialAttack = 30,
      specialDefense = 30,
      speed = 30
    )
  }

  @BeforeEach
  fun buffStats(): Stats {
    return Stats(
      kp = 40,
      attack = 2,
      defense = 2,
      specialAttack = 2,
      specialDefense = 2,
      speed = 2
    )
  }
  @BeforeEach
  fun deBuffstats(): Stats {
    return Stats(
      kp = 40,
      attack = -2,
      defense = -2,
      specialAttack = -2,
      specialDefense = -2,
      speed = -2
    )
  }

  @Test
  fun `Test takeDamage`() {
    val stats = stats1()
    stats.takeDamage(damage = 25)
    assertThat(stats.getKp()).isEqualTo(5)
  }

  @Test
  fun `Test buffsDebuffs only Buff`() {
    val stats = stats1()
    stats.buffsDebuffs(stats = buffStats(), heal = true)
    assertThat(stats.getKp()).isEqualTo(70)
    assertThat(stats.getAttack()).isEqualTo(60)
    assertThat(stats.getDefense()).isEqualTo(60)
    assertThat(stats.getSpecialAttack()).isEqualTo(60)
    assertThat(stats.getSpecialDefense()).isEqualTo(60)
    assertThat(stats.getSpeed()).isEqualTo(60)
  }

  @Test
  fun `Test buffsDebuffs only Debuff`() {
    val stats = stats1()
    stats.buffsDebuffs(stats = deBuffstats(), heal = false)
    assertThat(stats.getKp()).isEqualTo(0)
    assertThat(stats.getAttack()).isEqualTo(15)
    assertThat(stats.getDefense()).isEqualTo(15)
    assertThat(stats.getSpecialAttack()).isEqualTo(15)
    assertThat(stats.getSpecialDefense()).isEqualTo(15)
    assertThat(stats.getSpeed()).isEqualTo(15)
  }
  @Test
  fun `Test reduceSpeed`() {
    val stats = stats1()
    stats.reduceSpeed(0.6666666)
    assertThat(stats.getSpeed()).isEqualTo(11)
  }
  @Test
  fun `not allowed buff or debuff number`(){
    val exception=assertThrows(NoBuffOrDebuffValue::class.java){
        val changeStats = Stats(
          kp = 10,
          attack = 10,
          defense = 20,
          specialAttack = -30,
          specialDefense = -10,
          speed = 100
        )
      stats1().buffsDebuffs(stats = changeStats, heal = true)
    }
    assertEquals("The number 10 is a not allowed number for Buffs and Debuffs.", exception.message)
  }
}