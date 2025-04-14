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
    fun `DBHandler test`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv")
        assertThat(DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")).isNotNull
    }

    @Test
    fun `Quantomix test`() {
        val quantomix = Quantomix("Test", "hello", "world", 200, 100, 30, 70, 35, 88)
        assertThat(quantomix.quantomixName).isEqualTo("Test")
        assertThat(quantomix.typ1).isEqualTo("hello")
        assertThat(quantomix.typ2).isEqualTo("world")
        assertThat(quantomix.kp).isEqualTo(200)
        assertThat(quantomix.attack).isEqualTo(100)
        assertThat(quantomix.defense).isEqualTo(30)
        assertThat(quantomix.specialAttack).isEqualTo(70)
        assertThat(quantomix.specialDefense).isEqualTo(35)
        assertThat(quantomix.speed).isEqualTo(88)
    }

    @Test
    fun `Quantomix out of DB test`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv")
        val monster:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        assertThat(monster).isNotNull
        //assertThat(monster).shouldBeTypeOf<Quantomix>()
        assertThat(monster.kp).isEqualTo(78)
    }
}