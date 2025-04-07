package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import java.io.File

// TODO Delete this placeholder test.
class KotlinExampleTest : AnnotationSpec() {
  @Test
  fun `example returns hello world`() {
    assertThat(KotlinExample().sayHello()).isEqualTo("Hello World!")
  }
  @Test
  fun `DBHandler test` () {
    val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
    assertThat(DBHandler().getMonsterbyName(monsterDB, "Glurak")).isEqualTo(true)
  }
}