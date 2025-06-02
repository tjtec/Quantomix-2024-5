package hwr.oop.quantomix.fight.objects

enum class Status {
    NoDamage,
    Poison,
    StrongPoison,
    Combustion,
    Paralysis,
    Sleep,
    Confusion,
    Freeze;

    fun calculateStatusEffect(
        battleStats: BattleStats,
        previous: StatusHelper = StatusHelper(),
        setDurationForRounds: Int? = null
    ): StatusHelper {
        return when (this) {
            StrongPoison -> {
                val rounds = previous.alreadyPassedRounds + 1
                val newSummand = if (previous.alreadyPassedRounds == 0)
                    (battleStats.getStats().getKp() * 1 / 16)
                else previous.summand * 2
                StatusHelper(multiplicator = 1, summand = newSummand, alreadyPassedRounds = rounds)
            }
            Poison -> {
                val rounds = previous.alreadyPassedRounds + 1
                StatusHelper(multiplicator = 1, summand = (battleStats.getStats().getKp() * 1 / 16), alreadyPassedRounds = rounds)
            }
            Combustion -> {
                val rounds = previous.alreadyPassedRounds + 1
                StatusHelper(multiplicator = 1, summand = (battleStats.getStats().getKp() / 8), alreadyPassedRounds = rounds)
            }
            Paralysis -> {
                battleStats.getStats().reduceSpeed(0.5)
                val rounds = previous.alreadyPassedRounds + 1
                val m = UsefulInformationForStatus(previous.alreadyPassedRounds).hitParalysis()
                StatusHelper(multiplicator = m, summand = 0, alreadyPassedRounds = rounds)
            }
            Sleep -> {
                val rounds = previous.alreadyPassedRounds + 1
                val effectiveMultiplicator = if (setDurationForRounds != null) {
                    if (previous.alreadyPassedRounds < setDurationForRounds) 0 else 1
                } else {
                    if (UsefulInformationForStatus(previous.alreadyPassedRounds).roundsWithStatusEffectLeft()) 0 else 1
                }
                StatusHelper(multiplicator = effectiveMultiplicator, summand = 0, alreadyPassedRounds = rounds)
            }
            Confusion -> {
                val rounds = previous.alreadyPassedRounds + 1
                val effectiveMultiplicator = if (
                    UsefulInformationForStatus(previous.alreadyPassedRounds).selfHit() &&
                    UsefulInformationForStatus(previous.alreadyPassedRounds).roundsWithStatusEffectLeft()
                ) -1 else 1
                StatusHelper(multiplicator = effectiveMultiplicator, summand = 0, alreadyPassedRounds = rounds)
            }
            Freeze, NoDamage -> {
                StatusHelper(multiplicator = 0, summand = 0, alreadyPassedRounds = previous.alreadyPassedRounds)
            }
        }
    }
}