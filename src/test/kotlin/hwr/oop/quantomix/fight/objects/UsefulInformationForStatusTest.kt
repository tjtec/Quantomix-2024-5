package hwr.oop.quantomix.fight.objects

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class UsefulInformationForStatusTest : AnnotationSpec() {
  @Test
  fun `Test hitParalysis`() {
    val usefulInformationForStatus = UsefulInformationForStatus(0)
    assertThat(usefulInformationForStatus.hitParalysis(65)).isEqualTo(1)
    assertThat(usefulInformationForStatus.hitParalysis(66)).isEqualTo(0)
  }

  @Test
  fun `Test selfHit`() {
    val usefulInformationForStatus = UsefulInformationForStatus(0)
    assertThat(usefulInformationForStatus.selfHit(51)).isEqualTo(true)
    assertThat(usefulInformationForStatus.selfHit(50)).isEqualTo(false)
  }
  @Test
  fun `Test roundsWithStatusEffectLeft`() {
    var usefulInformationForStatus = UsefulInformationForStatus(0)
    assertThat(usefulInformationForStatus.roundsWithStatusEffectLeft(1)).isTrue()
    usefulInformationForStatus = UsefulInformationForStatus(1)
    assertThat(usefulInformationForStatus.roundsWithStatusEffectLeft(1)).isTrue()
    usefulInformationForStatus = UsefulInformationForStatus(2)
    assertThat(usefulInformationForStatus.roundsWithStatusEffectLeft(1)).isFalse()
  }
}