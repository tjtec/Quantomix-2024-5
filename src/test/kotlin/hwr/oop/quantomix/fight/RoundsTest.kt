package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.logic.Rounds
import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions

class RoundsTest: AnnotationSpec() {

//    @Test
//    fun `Rounds with two trainers`() {
//        val feuer= Typ("Feuer")
//        val geist = Typ("Geist")
//        val Fluch = Attack("Fluch", Typ("Geist"), 30, 100)
//        val Tackle = Attack("Tackle", Typ("Normal"), 40, 100)
//        val Glut = Attack("Glut", Typ("Feuer"), 30, 100)
//        val Spukball = Attack("Spukball", Typ("Geist"), 60, 100)
//        val quantomix= Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(Tackle, Glut, Spukball, Fluch))
//        val quantomix2= Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(Tackle, Glut, Spukball, Fluch))
//        val trainer1= Coach("Pepe", quantomix, quantomix, quantomix, quantomix, quantomix, quantomix)
//        val trainer2= Coach("Lilly", quantomix2, quantomix2, quantomix2, quantomix2, quantomix2, quantomix2)
//        Rounds(listOf(trainer1, trainer2)).start()
//        Assertions.assertThat(trainer1.quantomix1.kp == 0 || trainer2.quantomix1.kp == 0)
//    }
}