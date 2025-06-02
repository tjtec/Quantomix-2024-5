package hwr.oop.quantomix.objects

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TypTest2 : AnnotationSpec() {
    @Test
    fun `Test getFromString`() {
        assertThat(Typ.getFromString("Normal")).isEqualTo(Typ.Normal)
        assertThat(Typ.getFromString("Kampf")).isEqualTo(Typ.Kampf)
        assertThat(Typ.getFromString("Flug")).isEqualTo(Typ.Flug)
        assertThat(Typ.getFromString("Gift")).isEqualTo(Typ.Gift)
        assertThat(Typ.getFromString("Stahl")).isEqualTo(Typ.Stahl)
        assertThat(Typ.getFromString("Fee")).isEqualTo(Typ.Fee)
        assertThat(Typ.getFromString("Wasser")).isEqualTo(Typ.Wasser)
        assertThat(Typ.getFromString("Drache")).isEqualTo(Typ.Drache)
        assertThat(Typ.getFromString("Unlicht")).isEqualTo(Typ.Unlicht)
        assertThat(Typ.getFromString("Feuer")).isEqualTo(Typ.Feuer)
        assertThat(Typ.getFromString("Eis")).isEqualTo(Typ.Eis)
        assertThat(Typ.getFromString("Boden")).isEqualTo(Typ.Boden)
        assertThat(Typ.getFromString("Elektro")).isEqualTo(Typ.Elektro)
        assertThat(Typ.getFromString("Kaefer")).isEqualTo(Typ.Kaefer)
        assertThat(Typ.getFromString("Pflanze")).isEqualTo(Typ.Pflanze)
        assertThat(Typ.getFromString("Geist")).isEqualTo(Typ.Geist)
        assertThat(Typ.getFromString("Psycho")).isEqualTo(Typ.Psycho)
        assertThat(Typ.getFromString("Gestein")).isEqualTo(Typ.Gestein)
        assertThat(Typ.getFromString("Unbekannt")).isEqualTo(Typ.Normal)
    }

    @Test
    fun `Test getFromString with default type`() {
        Typ.entries.forEach { typ ->
            assertThat(Typ.getFromString(typ.name)).isEqualTo(typ)
        }
        assertThat(Typ.getFromString("feuer")).isEqualTo(Typ.Normal)
        assertThat(Typ.getFromString("Feuer")).isEqualTo(Typ.Feuer)
        assertThat(Typ.getFromString("Unbekannt")).isEqualTo(Typ.Normal)
        assertThat(Typ.getFromString("XYZ")).isEqualTo(Typ.Normal)
        assertThat(Typ.getFromString("")).isEqualTo(Typ.Normal)
        assertThat(Typ.getFromString(" ")).isEqualTo(Typ.Normal)
    }
}
