package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.types.shouldBeTypeOf
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.io.File

// TODO Delete this placeholder test.
class KotlinExampleTest : AnnotationSpec() {

    val monsterDBforTest = File("src/test/kotlin/hwr/oop/resources/test.csv");

    @Test
    fun `Quantomix Class test`() {
        val quantomix: Quantomix = Quantomix("Test", "hello", "world", 200, 100, 30, 70, 35, 88)
        assertThat(quantomix.quantomixName).isEqualTo("Test")
        assertThat(quantomix.typ1).isEqualTo("hello")
        assertThat(quantomix.typ2).isEqualTo("world")
        assertThat(quantomix.inputKp).isEqualTo(200)
        assertThat(quantomix.attack).isEqualTo(100)
        assertThat(quantomix.defense).isEqualTo(30)
        assertThat(quantomix.specialAttack).isEqualTo(70)
        assertThat(quantomix.specialDefense).isEqualTo(35)
        assertThat(quantomix.speed).isEqualTo(88)
    }

    @Test
    fun `createQuantomixObject`() {
        val monster:Quantomix=DBHandler().createQuantomixObject(monsterDBforTest, "Glurak")
        assertThat(monster).isNotNull
        assertThat(monster.quantomixName).isEqualTo("Glurak")
        assertThat(monster.typ1).isEqualTo("Feuer")
        assertThat(monster.typ2).isEqualTo("Flug")
        assertThat(monster.inputKp).isEqualTo(78)
        assertThat(monster.attack).isEqualTo(84)
        assertThat(monster.defense).isEqualTo(78)
        assertThat(monster.specialAttack).isEqualTo(109)
        assertThat(monster.specialDefense).isEqualTo(85)
        assertThat(monster.speed).isEqualTo(100)
    }

    @Test
    fun `createQuantomixObject2`() {
        val monster:Quantomix=DBHandler().createQuantomixObject(monsterDBforTest, "Glurak2")
        assertThat(monster).isNotNull
        assertThat(monster.quantomixName).isEqualTo("Glurak2")
        assertThat(monster.typ1).isEqualTo("Feuer")
        assertThat(monster.typ2).isEqualTo("none")
        assertThat(monster.inputKp).isEqualTo(78)
        assertThat(monster.attack).isEqualTo(84)
        assertThat(monster.defense).isEqualTo(78)
        assertThat(monster.specialAttack).isEqualTo(109)
        assertThat(monster.specialDefense).isEqualTo(85)
        assertThat(monster.speed).isEqualTo(100)
    }

    @Test
    fun `createQuantomixObject-FileError`() {
        val nonexistentDB = File("src/test.csirjgijeirgijeirv");
        assertThatThrownBy { DBHandler().createQuantomixObject(nonexistentDB, "uh4tuj3g8j34") }.message()
            .contains("Fehler beim Lesen der Datei")
    }

    @Test
    fun `createQuantomixObject-Not Found`() {
        assertThatThrownBy { DBHandler().createQuantomixObject(monsterDBforTest, "not found") }.message()
            .contains("No data found for file")
    }
}