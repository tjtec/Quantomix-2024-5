package hwr.oop.quantomix.stats

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*

class GameDataTest : AnnotationSpec() {
    @Test
    fun `getEffektivitaet test`() {
        val returnval = GameData.getEffektivitaet("Normal", "Kampf")
        assertThat(returnval).isEqualTo(2.0f)
    }
}