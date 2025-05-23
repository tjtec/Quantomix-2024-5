package hwr.oop.quantomix.fight

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Effects
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec

class RoundsTest : AnnotationSpec() {
    @BeforeEach
    fun testAttacks(): List<Attack> {
        val fluch = Attack(
            attackName = "Fluch",
            type = Typ.Geist,
            damage = 30,
            damageQuote = 100,
            specialAttack = true ,
            effects = mutableListOf< Effects>())
        val tackle = Attack(
            attackName = "Tackle",
            type = Typ.Normal,
            damage = 40,
            damageQuote = 100,
            specialAttack = false,
            effects =mutableListOf<Effects>() )
        val eissturm = Attack(
            attackName = "Eissturm",
            type = Typ.Eis,
            damage = 30,
            damageQuote = 100,
            specialAttack = false,
            effects = mutableListOf<Effects>())
        val funkensprung = Attack(
            attackName = "funkensprung",
            type = Typ.Elektro,
            damage = 60,
            damageQuote = 100,
            specialAttack = true,
            effects = mutableListOf<Effects>())
        return listOf(fluch, tackle, eissturm, funkensprung)
    }

    @BeforeEach
    fun testQuantomix1(): Quantomix {
        val testQuantomix1 = Quantomix(
            quantomixName = "Quantomix1",
            typ1 = Typ.Geist,
            typ2 = Typ.Feuer,
            stats = Stats(
                kp = 60,
                attack = 60,
                defense = 60,
                specialAttack = 60,
                specialDefense = 60,
                speed = 60
            ),
            attacks = testAttacks()
        )
        return testQuantomix1
    }

    @BeforeEach
    fun TestQuantomix2(): Quantomix {
        val testQuantomix2 = Quantomix(
            quantomixName = "Quantomix2",
            typ1 = Typ.Eis,
            typ2 = Typ.Elektro,
            stats = Stats(
                kp = 50,
                attack = 50,
                defense = 50,
                specialAttack = 50,
                specialDefense = 50,
                speed = 50
            ),
            attacks = testAttacks()
        )
        return testQuantomix2
    }
    @Test
    fun `Rounds with two trainers`(){

    }

    /*@Test
    fun `Rounds with two trainers`() {
        val feuer = Typ("Feuer")
        val geist = Typ("Geist")
        val Fluch = Attack("Fluch", Typ("Geist"), 30, 100)
        val Tackle = Attack("Tackle", Typ("Normal"), 40, 100)
        val Glut = Attack("Glut", Typ("Feuer"), 30, 100)
        val Spukball = Attack("Spukball", Typ("Geist"), 60, 100)
        val quantomix = Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(Tackle, Glut, Spukball, Fluch))
        val quantomix2 = Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(Tackle, Glut, Spukball, Fluch))
        val trainer1 = Coach("Pepe", listOf(quantomix))
        val trainer2 = Coach("Lilly", listOf(quantomix2))
        val winner = Rounds(listOf(trainer1, trainer2)).start()
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

    @Test
    fun `Players have more than one Quantomix in Battle`() {
        val feuer = Typ("Feuer")
        val geist = Typ("Geist")
        val Fluch = Attack("Fluch", Typ("Geist"), 30, 100)
        val Tackle = Attack("Tackle", Typ("Normal"), 40, 100)
        val Glut = Attack("Glut", Typ("Feuer"), 30, 100)
        val Spukball = Attack("Spukball", Typ("Geist"), 60, 100)
        val quantomix = Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(Tackle, Glut, Spukball, Fluch))
        val quantomix2 = Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(Tackle, Glut, Spukball, Fluch))
        val trainer1 = Coach("Pepe", listOf(quantomix, quantomix2))
        val trainer2 = Coach("Lilly", listOf(quantomix2, quantomix))
        val winner = Rounds(listOf(trainer1, trainer2)).start(2)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

    @Test
    fun `More than two Players`() {
        val feuer = Typ("Feuer")
        val geist = Typ("Geist")
        val Fluch = Attack("Fluch", Typ("Geist"), 30, 100)
        val Tackle = Attack("Tackle", Typ("Normal"), 40, 100)
        val Glut = Attack("Glut", Typ("Feuer"), 30, 100)
        val Spukball = Attack("Spukball", Typ("Geist"), 60, 100)
        val quantomix = Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(Tackle, Glut, Spukball, Fluch))
        val quantomix2 = Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(Tackle, Glut, Spukball, Fluch))
        val trainer1 = Coach("Pepe", listOf(quantomix, quantomix2))
        val trainer2 = Coach("Lilly", listOf(quantomix2, quantomix))
        val trainer3 = Coach("Gladio", listOf(quantomix, quantomix))
        val winner = Rounds(listOf(trainer1, trainer2, trainer3)).start()
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }

    fun `More than two Players and more than one Quantomix in Battle`() {
        val feuer = Typ("Feuer")
        val geist = Typ("Geist")
        val Fluch = Attack("Fluch", Typ("Geist"), 30, 100)
        val Tackle = Attack("Tackle", Typ("Normal"), 40, 100)
        val Glut = Attack("Glut", Typ("Feuer"), 30, 100)
        val Spukball = Attack("Spukball", Typ("Geist"), 60, 100)
        val quantomix = Quantomix("Test", feuer, geist, 60, 60, 60, 60, 60, 60, listOf(Tackle, Glut, Spukball, Fluch))
        val quantomix2 = Quantomix("Test", feuer, geist, 50, 50, 50, 50, 50, 50, listOf(Tackle, Glut, Spukball, Fluch))
        val trainer1 = Coach("Pepe", listOf(quantomix, quantomix2, quantomix))
        val trainer2 = Coach("Lilly", listOf(quantomix2, quantomix, quantomix2))
        val trainer3 = Coach("Gladio", listOf(quantomix, quantomix, quantomix2))
        val trainer4 = Coach("Flora", listOf(quantomix2, quantomix2, quantomix))
        val winner = Rounds(listOf(trainer1, trainer2, trainer3, trainer4)).start(3)
        Assertions.assertThat(winner).isEqualTo(trainer1)
    }*/

}