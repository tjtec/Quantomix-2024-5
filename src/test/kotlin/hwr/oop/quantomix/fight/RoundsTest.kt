package hwr.oop.quantomix.fight

import io.kotest.core.spec.style.AnnotationSpec
import hwr.oop.quantomix.fight.logic.Rounds
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import org.assertj.core.api.Assertions

class RoundsTest: AnnotationSpec() {
    val feuer= Typ("Feuer")
    val geist = Typ("Geist")
    val fluch = Attack("Fluch", Typ("Geist"), 150, 100)
    val tackle = Attack("Tackle", Typ("Normal"), 150, 100)
    val glut = Attack("Glut", Typ("Feuer"), 150, 100)
    val spukball = Attack("Spukball", Typ("Geist"), 150, 100)
    val quantomix= Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(tackle, glut, spukball, fluch))
    val quantomix2= Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(tackle, glut, spukball, fluch))
    val mutableListofAttacks= mutableListOf(tackle, glut, spukball, fluch, tackle, glut, spukball, fluch, tackle, glut, spukball, fluch)
    val mutableListofQuantomix= mutableListOf(quantomix2, quantomix,quantomix2, quantomix,quantomix2, quantomix,quantomix2, quantomix, quantomix2, quantomix,quantomix2, quantomix)

    @Test
     fun `Rounds with two trainers`() {
         val trainer1= Coach("Pepe", listOf(quantomix,quantomix, quantomix, quantomix, quantomix, quantomix))
         val trainer2= Coach("Lilly", listOf(quantomix2, quantomix2, quantomix2, quantomix2, quantomix2, quantomix2))
         val winner= Rounds(listOf(trainer1, trainer2)).start(1, mutableListofAttacks, mutableListofQuantomix)
         Assertions.assertThat(winner).isEqualTo(trainer1)
     }

    @Test
    fun `Players have more than one Quantomix in Battle`() {
        val trainer1= Coach("Pepe", listOf(quantomix, quantomix2))
        val trainer2= Coach("Lilly", listOf(quantomix2, quantomix))
        val winner= Rounds(listOf(trainer1, trainer2)).start(2)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }
    @Test
    fun `More than two Players`(){
        val trainer1= Coach("Pepe", listOf(quantomix, quantomix2))
        val trainer2= Coach("Lilly", listOf(quantomix2, quantomix))
        val trainer3= Coach("Gladio", listOf(quantomix, quantomix))
        val winner= Rounds(listOf(trainer1, trainer2, trainer3)).start(1)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

    fun `More than two Players and more than one Quantomix in Battle`() {
        val trainer1= Coach("Pepe", listOf(quantomix, quantomix2, quantomix))
        val trainer2= Coach("Lilly", listOf(quantomix2, quantomix, quantomix2))
        val trainer3= Coach("Gladio", listOf(quantomix, quantomix, quantomix2))
        val trainer4= Coach("Flora", listOf(quantomix2, quantomix2, quantomix))
        val winner= Rounds(listOf(trainer1, trainer2, trainer3, trainer4)).start(3)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

}