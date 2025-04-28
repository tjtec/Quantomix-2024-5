package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class RoundsTest:AnnotationSpec() {
    @Test
    fun `Rounds with two trainers`() {
        val Fluch = Attack("Fluch", "Geist", 30, 100, "effiktiv")
        val Tackle = Attack("Tackle", "Normal", 40, 100, "effiktiv")
        val Glut = Attack("Glut", "Feuer", 30, 100, "effiktiv")
        val Spukball = Attack("Spukball", "Geist", 60, 100, "effiktiv")
        val quantomix=Quantomix("Test", "Feuer", "Geist", 60, 60,60,60,60,60, listOf(Tackle, Glut, Spukball, Fluch))
        val quantomix2=Quantomix("Test", "Feuer", "Geist", 50, 50,50,50,50,50, listOf(Tackle, Glut, Spukball, Fluch))
        val trainer1=Coach("Pepe", quantomix, quantomix, quantomix, quantomix,quantomix,quantomix)
        val trainer2=Coach("Lilly", quantomix2, quantomix2, quantomix2, quantomix2, quantomix2,quantomix2)
        RoundsWith2Trainers(trainer1, trainer2).start()
        assertThat(trainer1.quantomix1.kp==0 || trainer2.quantomix1.kp==0)
    }
}