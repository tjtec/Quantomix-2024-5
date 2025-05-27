package hwr.oop.quantomix.fight.objects

class StatusHelper(
    val multiplicator: Int = 1,
    val summand: Int = 0,
    val usefulInformationForStatus: UsefulInformationForStatus? = null
) {
    fun setHelper(
        overrideMuliplicator: Int = multiplicator,
        overrideSummand: Int = summand,
        overrideUsefulInformationForStatus: UsefulInformationForStatus? = usefulInformationForStatus
    ): StatusHelper {
        return StatusHelper(overrideMuliplicator, overrideSummand)
    }

    fun selfHit(): Boolean {
        return when (multiplicator) {
            -1 -> true
            else -> false
        }
    }
}