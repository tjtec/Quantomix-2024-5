package hwr.oop.quantomix.fight.objects

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

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
    stats.buffsDebuffs(stats = buffStats(), buff = true)
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
    stats.buffsDebuffs(stats = deBuffstats(), buff = false)
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
}