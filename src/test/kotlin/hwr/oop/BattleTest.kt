package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import java.io.File

class BattleTest : AnnotationSpec() {
    @Test
    fun `Batteltest with normal Attack`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
        val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Schillok")
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("tackle", "normal", 40, 100, "normal")
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(41)
    }
    @Test
    fun `Batteltest with special Attack Type 2`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
        val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Schillok")
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Pflücker", "Flug", 40, 100, "effektiv")
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(31)
    }
    @Test
    fun `Batteltest with other Quantomix and special Attack Type 1`() {
        val monsterDB = File("src/main/kotlin/hwr/oop/resources/test.csv");
        val monster1:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Glurak")
        val monster2:Quantomix=DBHandler().getMonsterbyNameObject(monsterDB, "Tauboss")
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Glut", "Feuer", 40, 100, "nicht effektiv")
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(61)
        assertThat(monster2.kp).isEqualTo(83)
    }
}