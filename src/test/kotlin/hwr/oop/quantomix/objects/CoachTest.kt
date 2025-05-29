package hwr.oop.quantomix.objects

import hwr.oop.quantomix.fight.objects.Attack
import hwr.oop.quantomix.fight.objects.Stats
import hwr.oop.quantomix.monster.Quantomix
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class CoachTest: AnnotationSpec() {
    @BeforeEach
    fun quantomix(): Quantomix {
        return Quantomix(
            quantomixName = "Digda",
            typ1 = Typ.Boden,
            typ2 = null,
            stats = Stats(
                kp = 50,
                attack = 50,
                defense = 50,
                specialAttack = 50,
                specialDefense = 50,
                speed = 50
            ),
            attacks = listOf(Attack(
                attackName = "Erdbeben",
                type = Typ.Boden,
                damage = 80,
                damageQuote = 100,
                specialAttack = false
            )),
        )
    }
    @Test
    fun `Test Coach is working correctly`() {
        val quantomix = quantomix()
        val coach = Coach(coachName = "Anastasia",
            listOf(quantomix))
        assertThat(coach.getFirstQuantomix()).isEqualTo(quantomix)
        assertThat(coach.coachName).isEqualTo("Anastasia")
        assertThat(coach.quantomixTeam).isEqualTo(mutableListOf(quantomix))
    }
}