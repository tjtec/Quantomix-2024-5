package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Rounds
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class RoundsTest: AnnotationSpec() {
    val feuer= Typ("Feuer")
    val geist = Typ("Geist")
    val fluch = Attack("Fluch", Typ("Geist"), 150, 100)
    val tackle = Attack("Tackle", Typ("Normal"), 150, 100)
    val glut = Attack("Glut", Typ("Geist"), 150, 100)
    val spukball = Attack("Spukball", Typ("Normal"), 150, 100)
    val quantomix1 = Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix11 = Quantomix("Test", feuer, geist, 61, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix111 = Quantomix("Test", feuer, geist, 62, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix1111 = Quantomix("Test", feuer, geist, 63, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix11111 = Quantomix("Test", feuer, geist, 64, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix111111 = Quantomix("Test", feuer, geist, 65, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix2= Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val quantomix22 = Quantomix("Test", feuer, geist, 51, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val quantomix222 = Quantomix("Test", feuer, geist, 52, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val quantomix2222 = Quantomix("Test", feuer, geist, 53, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val quantomix22222 = Quantomix("Test", feuer, geist, 54, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val quantomix222222 = Quantomix("Test", feuer, geist, 55, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val mutableListofAttacks = mutableListOf(
        tackle,
        glut,
        spukball,
        fluch,
        tackle,
        glut,
        spukball,
        fluch,
        tackle,
        glut,
        spukball,
        fluch,
        tackle,
        glut,
        spukball,
        fluch,
        tackle,
        glut,
        spukball,
        fluch,
        tackle,
        glut,
        spukball,
        fluch,
        tackle,
        glut,
        spukball,
        fluch,
    )
    val mutableListofTargets = mutableListOf(
        quantomix2,
        quantomix1,
        quantomix2,
        quantomix11,
        quantomix22,
        quantomix11,
        quantomix22,
        quantomix111,
        quantomix222,
        quantomix111,
        quantomix222,
        quantomix1111,
        quantomix2222,
        quantomix1111,
        quantomix2222,
        quantomix11111,
        quantomix22222,
        quantomix11111,
        quantomix22222,
        quantomix111111,
        quantomix222222,
        quantomix111111,
        quantomix222222,
        quantomix111111,
        quantomix222222,
        quantomix111111
    )
    val mutableListofQuantomix = mutableListOf(
        quantomix11,
        quantomix22,
        quantomix111,
        quantomix222,
        quantomix1111,
        quantomix2222,
        quantomix11111,
        quantomix22222,
        quantomix111111,
        quantomix222222
    )
    val trainer1 =
        Coach("Pepe", listOf(quantomix1, quantomix11, quantomix111, quantomix1111, quantomix11111, quantomix111111))
    val trainer2 =
        Coach("Lilly", listOf(quantomix2, quantomix22, quantomix222, quantomix2222, quantomix22222, quantomix222222))

    @Test
     fun `Rounds with two trainers`() {
        quantomix1.battleStats.trainer = trainer1
        quantomix11.battleStats.trainer = trainer1
        quantomix111.battleStats.trainer = trainer1
        quantomix1111.battleStats.trainer = trainer1
        quantomix11111.battleStats.trainer = trainer1
        quantomix111111.battleStats.trainer = trainer1
        quantomix2.battleStats.trainer = trainer2
        quantomix22.battleStats.trainer = trainer2
        quantomix222.battleStats.trainer = trainer2
        quantomix2222.battleStats.trainer = trainer2
        quantomix22222.battleStats.trainer = trainer2
        quantomix222222.battleStats.trainer = trainer2
        val winner = Rounds(listOf(trainer1, trainer2)).start(
            1,
            mutableListofAttacks,
            mutableListofTargets,
            mutableListofQuantomix
        )
        Assertions.assertThat(winner).isEqualTo(trainer2)
        //ToDo:Warum laufen wir in eine Endlosschleife
     }

    @Test
    fun `Players have more than one Quantomix in Battle`() {
        val trainer1 =
            Coach("Pepe", listOf(quantomix1, quantomix11, quantomix111, quantomix1111, quantomix11111, quantomix111111))
        val trainer2 = Coach("Lilly", listOf(quantomix2, quantomix1))
        val winner= Rounds(listOf(trainer1, trainer2)).start(2)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }
    @Test
    fun `More than two Players`(){
        val trainer1 =
            Coach("Pepe", listOf(quantomix1, quantomix11, quantomix111, quantomix1111, quantomix11111, quantomix111111))
        val trainer2 = Coach("Lilly", listOf(quantomix2, quantomix1))
        val trainer3 = Coach("Gladio", listOf(quantomix1, quantomix1))
        val winner= Rounds(listOf(trainer1, trainer2, trainer3)).start(1)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

    fun `More than two Players and more than one Quantomix in Battle`() {
        val trainer1 =
            Coach("Pepe", listOf(quantomix1, quantomix11, quantomix111, quantomix1111, quantomix11111, quantomix111111))
        val trainer2 = Coach("Lilly", listOf(quantomix2, quantomix1, quantomix2))
        val trainer3 = Coach("Gladio", listOf(quantomix1, quantomix1, quantomix2))
        val trainer4 = Coach("Flora", listOf(quantomix2, quantomix2, quantomix1))
        val winner= Rounds(listOf(trainer1, trainer2, trainer3, trainer4)).start(3)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

}