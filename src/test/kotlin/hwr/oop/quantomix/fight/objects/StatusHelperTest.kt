package hwr.oop.quantomix.fight.objects

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class StatusHelperTest : AnnotationSpec() {
  @Test
  fun `Test selfHit`() {
    val statusHelper = StatusHelper(multiplicator = -1)
    assertThat(statusHelper.selfHit()).isTrue
    val statusHelper2 = StatusHelper(multiplicator = 1)
    assertThat(statusHelper2.selfHit()).isFalse

  }
}