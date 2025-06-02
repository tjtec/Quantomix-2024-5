package hwr.oop.quantomix.fight.objects

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TestStats: AnnotationSpec() {
    @BeforeEach
    fun stats1(): Stats{
        return Stats(kp = 30,
            attack = 30,
            defense = 30,
            specialAttack = 30,
            specialDefense = 30,
            speed = 30)
    }
    @BeforeEach
    fun stats3(): Stats {
        return Stats(kp = 40,
            attack = 2,
            defense = 2,
            specialAttack= 2,
            specialDefense=2,
            speed = 2)
    }
    @Test
    fun `Test newKp`(){
        val stats = stats1()
        stats.newKp(damage = 25)
        assertThat(stats.getKp()).isEqualTo(5)
    }
    @Test
    fun `Test buffsDebuffs only Buff`(){
        val stats = stats1()
        val solution=stats.buffsDebuffs(stats=stats3(),buff = true)
        assertThat(stats.getKp()).isEqualTo(70)
        assertThat(stats.getAttack()).isEqualTo(32)
        assertThat(stats.getDefense()).isEqualTo(32)
        assertThat(stats.getSpecialAttack()).isEqualTo(32)
        assertThat(stats.getSpecialDefense()).isEqualTo(32)
        assertThat(stats.getSpeed()).isEqualTo(32)
        assertThat(solution).isEqualTo(true)
    }
    @Test
    fun `Test buffsDebuffs only Debuff`(){
        val stats = stats1()
        val solution=stats.buffsDebuffs(stats=stats3(),buff = false)
        assertThat(stats.getKp()).isEqualTo(0)
        assertThat(stats.getAttack()).isEqualTo(28)
        assertThat(stats.getDefense()).isEqualTo(28)
        assertThat(stats.getSpecialAttack()).isEqualTo(28)
        assertThat(stats.getSpecialDefense()).isEqualTo(28)
        assertThat(stats.getSpeed()).isEqualTo(28)
        assertThat(solution).isEqualTo(true)
    }
    @Test
    fun `Test fueseToStats`(){
        val stats = stats1()
        stats.fueseToStats(stats=stats3())
        assertThat(stats.getKp()).isEqualTo(60)
        assertThat(stats.getAttack()).isEqualTo(60)
        assertThat(stats.getDefense()).isEqualTo(60)
        assertThat(stats.getSpecialDefense()).isEqualTo(60)
        assertThat(stats.getSpeed()).isEqualTo(60)
        assertThat(stats.getSpecialAttack()).isEqualTo(60)
    }
}