package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import java.io.File

class BattleTest : AnnotationSpec() {
    @Test
    fun `Batteltest with normal Attack`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix= DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix= DBHandler().createQuantomixObject(monsterDB, "Schillok")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("tackle", "normal", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(41)
    }
    @Test
    fun `Batteltest with special Attack Type 2`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Schillok")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Pflücker", "Flug", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(31)
    }
    @Test
    fun `Batteltest with other Quantomix and special Attack Type 1`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Tauboss")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Glut", "Feuer", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(61)
        assertThat(monster2.kp).isEqualTo(83)
    }
    @Test
    fun `Batteltest extrem effectiviness`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Owei")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Glut", "Feuer", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(32)
    }
    @Test
    fun `Batteltest less effectiviness`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Owei")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Tackle", "Normal", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(51)
    }
    @Test
    fun `Batteltest effectiviness`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Owei")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Test", "Gift", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(42)
    }
    @Test
    fun `Batteltest not effectiv`() {
        //ToDo: make test work without the monsterDB
        val monsterDB = GameData().monsterDB;
        val monster1:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Glurak")!!
        val monster2:Quantomix=DBHandler().createQuantomixObject(monsterDB, "Rattfratz")!!
        val battle = Battle(monster1, monster2)
        val attack:Attack=Attack("Test", "Kampf", 40, 100)
        battle.newKp(attack)
        assertThat(monster1.kp).isEqualTo(78)
        assertThat(monster2.kp).isEqualTo(30)
    }
}