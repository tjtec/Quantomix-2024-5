package hwr.oop.quantomix.fight.objects

import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Typ
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.AnnotationSpec.assertThat

class AttackTest : AnnotationSpec() {
    @BeforeEach
    fun spukball(): Attack {
        val attack = Attack(
            attackName = "Giftmüll",
            type = Typ.Gift,
            damage = 50,
            damageQuote = 100,
            specialAttack = true,
            effects = mutableListOf(
                Effects(
                    buff = false,
                    changeStats = Stats(
                        kp = 10,
                        attack = 4,
                        defense = 3,
                        specialAttack = 2,
                        specialDefense = 1,
                        speed = 0
                    ),
                    self = true
                )
            ),
            status = Status.StrongPoison
        )
        return attack
    }

    @Test
    fun `Test updateSelfDebuffs`() {
        val attack = spukball()
        val succes = attack.updateSelfDebuffs(
            stats = Stats(
                kp = 6,
                attack = 5,
                defense = 4,
                specialAttack = 3,
                specialDefense = 2,
                speed = 1
            )
        )
        assertThat(succes).isEqualsTo(true)
    }

    fun `Test changeStatsAndStatus`() {
        val attack = spukball()
        val attacker = Quantomix(
            quantomixName = "Slima",
            typ1 = Typ.Gift,
            stats = Stats(
                kp = 10,
                attack = 10,
                defense = 20,
                specialAttack = 10,
                specialDefense = 20,
                speed = 10
            ),
            attacks = ListOf(attack)
        )
        attacker.newBattleStats()

        )
    }
}