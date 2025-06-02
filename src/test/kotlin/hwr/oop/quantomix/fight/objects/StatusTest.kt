package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class StatusTest : AnnotationSpec() {
  @BeforeEach
  fun battleStats(): BattleStats {
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
  fun `Test NoDamage`() {
    val statusHelper =
      Status.NoDamage.calculateStatusEffect(battleStats())
    assertThat(statusHelper.multiplicator).isEqualTo(
      0
    )

  }

  @Test
  fun `Test Freeze`() {
    val statusHelper =
      Status.Freeze.calculateStatusEffect(
        battleStats()
      )
    assertThat(statusHelper.multiplicator).isEqualTo(
      0
    )
  }

  @Test
  fun `Test StrongPoison`() {
    val statusHelper = Status.StrongPoison.calculateStatusEffect(
      battleStats()
    )
    assertThat(statusHelper.summand).isEqualTo(3)
    val statusHelperRound2 = Status.StrongPoison.calculateStatusEffect(
      battleStats(), statusHelper
    )
    assertThat(statusHelperRound2.summand).isEqualTo(6)
    val statusHelperRound3 = Status.StrongPoison.calculateStatusEffect(
      battleStats(), statusHelperRound2
    )
    assertThat(statusHelperRound3.summand).isEqualTo(12)
    val statusHelperRound4 = Status.StrongPoison.calculateStatusEffect(
      battleStats(), statusHelperRound3
    )
    assertThat(statusHelperRound4.summand).isEqualTo(24)
    val statusHelperRound5 = Status.StrongPoison.calculateStatusEffect(
      battleStats(), statusHelperRound4
    )
    assertThat(statusHelperRound5.summand).isEqualTo(48)
  }

  @Test
  fun `Test Poison`() {
    val statusHelper = Status.Poison.calculateStatusEffect(battleStats())
    assertThat(statusHelper.summand).isEqualTo(3)
  }

  @Test
  fun `Test Combustion`() {
    val statusHelper = Status.Combustion.calculateStatusEffect(battleStats())
    assertThat(statusHelper.summand).isEqualTo(6)
  }

  @Test
  fun `Test Paralysis`() {
    val battleStats = battleStats()
    val statusHelper = Status.Paralysis.calculateStatusEffect(
      battleStats,
      StatusHelper(), 3
    )
    assertThat(statusHelper.multiplicator).isEqualTo(0)
    assertThat(battleStats.getStats().getSpeed()).isEqualTo(25)
  }

  @Test
  fun `Test Sleep`() {
    val statusHelper = Status.Sleep.calculateStatusEffect(
      battleStats(), previous = StatusHelper(), 4
    )
    assertThat(statusHelper.multiplicator).isEqualTo(0)
    val statusHelperRound1 = Status.Sleep.calculateStatusEffect(
      battleStats(), statusHelper, 4
    )
    assertThat(statusHelperRound1.multiplicator).isEqualTo(0)
    val statusHelperRound2 = Status.Sleep.calculateStatusEffect(
      battleStats(), statusHelperRound1, 4
    )
    assertThat(statusHelperRound2.multiplicator).isEqualTo(0)
    val statusHelperRound3 = Status.Sleep.calculateStatusEffect(
      battleStats(), statusHelperRound2, 4
    )
    assertThat(statusHelperRound3.multiplicator).isEqualTo(0)
    val statusHelperRound4 = Status.Sleep.calculateStatusEffect(
      battleStats(), statusHelperRound3, 4
    )
    assertThat(statusHelperRound4.multiplicator).isEqualTo(1)
    val statusHelperRound5 = Status.Sleep.calculateStatusEffect(
      battleStats(), statusHelperRound4, 4
    )
    assertThat(statusHelperRound5.multiplicator).isEqualTo(1)
  }
}