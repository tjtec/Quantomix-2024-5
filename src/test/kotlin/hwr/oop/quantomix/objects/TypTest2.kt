package hwr.oop.quantomix.objects

import hwr.oop.quantomix.Exceptions.NotAllowedTyp
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows

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
  }

  @Test
  fun `Test getFromString with wrong type`() {
    Typ.entries.forEach { typ ->
      assertThat(Typ.getFromString(typ.name)).isEqualTo(typ)
    }
    val exception = assertThrows(NotAllowedTyp::class.java) {
      Typ.getFromString("feuer")
    }
    assertThat(exception.message).isEqualTo(
      "feuer is not an allowed type. The following types are possible:" +
          "Normal; Kampf; Flug; Gift; Stahl; Fee; Wasser; Drache; Unlicht; " +
          "Feuer; Eis; Boden; Elektro; Kaefer;" +
          " Pflanze; Geist; Psycho; Gestein "
    )
  }
}
