package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import java.io.File

class BattleTest : AnnotationSpec() {
    @Test
    fun `Batteltest`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
        val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Schillok")
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("tackle", "normal", 40, 100, "normal")
        battle.newKp(attack)
        assertThat(monster1.inputKp).isEqualTo(78)
        assertThat(monster2.inputKp).isEqualTo(28)
    }
    @Test
    fun `Batteltest with special Attack`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
        val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Schillok")
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Pflücker", "Flug", 40, 100, "effektiv")
        battle.newKp(attack)
        assertThat(monster1.inputKp).isEqualTo(78)
        assertThat(monster2.inputKp).isEqualTo(28)
    }
    @Test
    fun `Batteltest with other Quantomix`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
        val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Tauboss")
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Glut", "Feuer", 40, 100, "nicht effektiv")
        battle.newKp(attack)
        assertThat(monster1.inputKp).isEqualTo(27)
        assertThat(monster2.inputKp).isEqualTo(83)
    }
}