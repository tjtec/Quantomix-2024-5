package hwr.oop.quantomix.fight.objects

class Stats(
    var kp: Int,
    var attack: Int,
    var defense: Int,
    var specialAttack: Int,
    var specialDefense: Int,
    var speed: Int,
) {
    fun deepCopy(
        kp: Int = this.kp,
        attack: Int = this.attack,
        defense: Int = this.defense,
        specialAttack: Int = this.specialAttack,
        specialDefense: Int = this.specialDefense,
        speed: Int = this.speed
    ) = Stats(
        kp, attack, defense, specialAttack, specialDefense, speed
    )

    fun BuffsDebuffs(stats: Stats, buff: Boolean) {
        if (!buff) {
            selfDamage(stats)
            newAttackValueDown(stats)
            newDefenseValueDown(stats)
            newSpecialAttackValueDown(stats)
            newSpecialDefenseValueDown(stats)
            newSpeedValueDown(stats)
        } else {
            heal(stats)
            newAttackValueUp(stats)
            newDefenseValueUp(stats)
            newSpecialAttackValueUp(stats)
            newSpecialDefenseValueUp(stats)
            newSpeedValueUp(stats)
        }
    }

    private fun selfDamage(stats: Stats) {
        val solution = this.kp - stats.kp
        if (solution < 0) {
            this.kp = 0
        } else {
            this.kp = solution
        }
    }

    private fun heal(stats: Stats) {
        this.kp += stats.kp
    }

    private fun newAttackValueDown(stats: Stats) {
        this.attack -= stats.attack
    }

    private fun newDefenseValueDown(stats: Stats) {
        this.defense -= stats.defense
    }

    private fun newSpecialAttackValueDown(stats: Stats) {
        this.specialAttack -= stats.specialAttack
    }

    private fun newSpecialDefenseValueDown(stats: Stats) {
        this.specialDefense -= stats.specialDefense
    }

    private fun newSpeedValueDown(stats: Stats) {
        this.speed -= stats.speed
    }

    private fun newAttackValueUp(stats: Stats) {
        this.attack += stats.attack
    }

    private fun newDefenseValueUp(stats: Stats) {
        this.defense += stats.defense
    }

    private fun newSpecialAttackValueUp(stats: Stats) {
        this.specialAttack += stats.specialAttack
    }

    private fun newSpecialDefenseValueUp(stats: Stats) {
        this.specialDefense += stats.specialDefense
    }

    private fun newSpeedValueUp(stats: Stats) {
        this.speed += stats.speed
    }
}
